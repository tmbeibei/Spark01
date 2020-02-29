package com.atguigu.akka.yellowserver.server

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import com.atguigu.akka.yellowserver.common.{ClientMessage, ServerMessage}
import com.typesafe.config.ConfigFactory


/**
 * @author tianmin
 * @date 2020/2/23 0023
 * @notes
 */
class YellowServer extends Actor{
  override def receive: Receive = {
    case "start"=>{
      println("start 小黄鸡服务器开始工作")
    }
    case ClientMessage(msg)=>{
      msg match {
        case "大数据学费" => sender() ! ServerMessage("大数据学费20000RMB")
        case "地址" => sender() ! ServerMessage("北京昌平区xx路")
        case "专业" => sender() ! ServerMessage("大数据 Pathoy 前端")
        case _ => sender() ! ServerMessage("说什么啊")
      }
    }
  }
}

object YellowServer extends App {
  val host = "127.0.0.1"
  val port = 9999
  //创建config对象,指定协议类型，监听的ip和端口
  val config = ConfigFactory.parseString(
    s"""
       |akka.actor.provider="akka.remote.RemoteActorRefProvider"
       |akka.remote.netty.tcp.hostname=$host
       |akka.remote.netty.tcp.port=$port
        """.stripMargin)

  // 创建ActorSystem
  private val actorSystem = ActorSystem("serverak",config)

  // 创建Actor
  private val yellowServer: ActorRef = actorSystem.actorOf(Props[YellowServer],"YellowServer")

  // 启动
  yellowServer ! "start"
}
