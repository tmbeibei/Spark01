package com.atguigu.akka.spark.master

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import com.atguigu.akka.spark.common._
import com.typesafe.config.ConfigFactory

import scala.collection.mutable
import scala.concurrent.duration._

/**
 * @author tianmin
 * @date 2020/2/23 0023
 * @notes
 */
class SparkMaster extends Actor {

  //workers 存放worker信息
  val workers = new mutable.HashMap[String, WorkerInfo]

  override def receive: Receive = {
    case "start" => {
      println("Master启动...")
    }
    // 接收到worker发送过来的注册信息；
    // 注册成功发送RegisteredWorkerInfo，代表成功
    case RegisterWorkerInfo(id, cpu, ram) => {
      // 初始化WorkerInfo对象
      val workerInfo = new WorkerInfo(id, cpu, ram)
      workerInfo.lastHeartBeat = System.currentTimeMillis()

      if (!workers.contains(id)) {
        // 添加
        workers += (id -> workerInfo)
        sender() ! RegisteredWorkerInfo

        println("worker:" + workerInfo)
      }

      // 发起worker定时检查任务
      self ! StartTimeOutWorker
    }
    // worker发送过来的心跳数据，更新改ID最后一次的发送时间
    case HeartBeat(id) => {
      // 更新lastHeartBeat时间
      val workerInfo = workers(id)
      workerInfo.lastHeartBeat = System.currentTimeMillis()

      // 写回workers
      workers(id) = workerInfo
      println("接收心跳Id:" + id)
    }
    // 发起定时检查worker，9秒一次
    case StartTimeOutWorker => {
      // 发起worker定时检查任务，每隔9秒发起一次
      import context.dispatcher
      context.system.scheduler.schedule(0 millis, 9000 millis, self, RemoveTimeOutWorker)
    }
    // 检查worker中，（当前时间 - lastHeartBean）>= 6000
    // 检查出来的删除掉
    case RemoveTimeOutWorker => {
      val startCount = workers.size
      workers.values.par.filter(item => (System.currentTimeMillis() - item.lastHeartBeat) > 6000)
        .map(item=>workers.remove(item.id))
      val endCount = workers.size
      println("总共删除:" + (startCount - endCount))
    }
  }
}

object SparkMaster extends App {

  if(args.length < 3){
    println("请输入参数 host port name")
    System.exit(1)
  }

  val host = args(0)
  val port = args(1).toInt
  val name = args(2)
  //创建config对象,指定协议类型，监听的ip和端口
  val config = ConfigFactory.parseString(
    s"""
       |akka.actor.provider="akka.remote.RemoteActorRefProvider"
       |akka.remote.netty.tcp.hostname=$host
       |akka.remote.netty.tcp.port=$port
        """.stripMargin)

  // 1 创建ActorSystem
  private val sparkMaster = ActorSystem("SparkMaster", config)

  // 2 获取ActorRef 引用
  private val sparkMasterRef: ActorRef = sparkMaster.actorOf(Props[SparkMaster], name)

  // 3 启动
  sparkMasterRef ! "start"

}
