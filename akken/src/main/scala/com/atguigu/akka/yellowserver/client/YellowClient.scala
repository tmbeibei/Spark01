package com.atguigu.akka.yellowserver.client

import akka.actor.{Actor, ActorRef, ActorSelection, ActorSystem, Props}
import com.atguigu.akka.yellowserver.common.{ClientMessage, ServerMessage}
import com.typesafe.config.ConfigFactory

import scala.io.StdIn

/**
 * @author tianmin
 * @date 2020/2/23 0023
 * @notes
 */
class YellowClient(serverHost: String, serverPort: Int) extends Actor {
  //服务器端对象
  var actorServerRef: ActorSelection = _

  override def preStart(): Unit = {
    println("preStart 开始执行")
    actorServerRef = context.actorSelection(s"akka.tcp://serverak@$serverHost:$serverPort/user/YellowServer")
    //println("actorServerRef=" + actorServerRef)
  }

  override def receive: Receive = {
    case "start" => println("start,客户端运行，可以咨询问题")
    case msg:String => actorServerRef ! ClientMessage(msg)
    case ServerMessage(msg) =>{
      println("接收到服务器回复:" + msg)
    }
  }
}

object YellowClient extends App {
  val clientHost = "127.0.0.1"
  val clientPort = 9998
  val serverHost = "127.0.0.1"
  val serverPort = 9999
  val config = ConfigFactory.parseString(
    s"""
       |akka.actor.provider="akka.remote.RemoteActorRefProvider"
       |akka.remote.netty.tcp.hostname=$clientHost
       |akka.remote.netty.tcp.port=$clientPort
        """.stripMargin)

  private val client = ActorSystem("client",config)
  private val yellowClient: ActorRef = client.actorOf(Props(new YellowClient(serverHost,serverPort)),"YellowClient")

  yellowClient ! "start"

  var loop = true
  while (loop){
    println("请输入咨询的问题")
    var mes = StdIn.readLine()
    if(mes.toLowerCase.equals("exit")){
      loop = true
    }

    yellowClient ! mes
  }
}
