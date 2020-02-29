package com.atguigu.akka.spark.worker

import java.util.UUID

import akka.actor.{Actor, ActorRef, ActorSelection, ActorSystem, Props}
import com.atguigu.akka.spark.common.{HeartBeat, RegisterWorkerInfo, RegisteredWorkerInfo, SendHeartBeat}
import com.typesafe.config.ConfigFactory

import scala.concurrent.duration._

/**
 * @author tianmin
 * @date 2020/2/23 0023
 * @notes
 */
class SparkWorker(masterHost: String, masterPort: Int, sparkMastName: String) extends Actor {
  // master对象
  var actorProxy: ActorSelection = _
  val id: String = UUID.randomUUID().toString

  // 初始化actorProxy
  override def preStart(): Unit = {
    actorProxy = context.actorSelection(s"akka.tcp://SparkMaster@${masterHost}:${masterPort}/user/${sparkMastName}")
    println("actorProxy=" + actorProxy)
  }

  override def receive: Receive = {
    case "start" => {
      println("worker启动...")
      //向master发送注册信息
      val registerWorkerInfo = new RegisterWorkerInfo(id, 16, 1024 * 1024 * 16)
      actorProxy ! registerWorkerInfo
    }
    //注册成功
    case RegisteredWorkerInfo => {
      println("worker注册成功ID:" + id)

      // 建立发送心跳任务,没3秒一次
      import context.dispatcher
      context.system.scheduler.schedule(0 millis, 3000 millis, self, SendHeartBeat)
    }
    // 向Master发送心跳
    case SendHeartBeat => {
      actorProxy ! HeartBeat(id)
      println("发送心跳ID：" + id)
    }
  }
}

object SparkWorker extends App {

  if(args.length < 6){
    println("请输入参数")
    sys.exit()
  }

  val workerHost = args(0)
  val workerPort = args(1).toInt
  val sparkWorkerName = args(2)

  val masterHost = args(3)
  val masterPort = args(4).toInt
  val sparkMastName = args(5)

  val config = ConfigFactory.parseString(
    s"""
       |akka.actor.provider="akka.remote.RemoteActorRefProvider"
       |akka.remote.netty.tcp.hostname=$workerHost
       |akka.remote.netty.tcp.port=$workerPort
        """.stripMargin)

  // 1 获取ActorSystem
  private val sparkWorker = ActorSystem("SparkWorker", config)

  // 2 获取ActorRef
  private val sparkWorkerRef: ActorRef = sparkWorker.actorOf(Props(new SparkWorker(masterHost, masterPort, sparkMastName)), sparkWorkerName)

  // 3 启动
  sparkWorkerRef ! "start"
}
