package com.atguigu.akkn.actor

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

/**
 * @author tianmin
 * @date 2020/2/23 0023
 * @notes
 */
class SayHelloActor extends Actor {
  //PartialFunction[Any, Unit]
  override def receive: Receive = {
    case "hello" => println("收到Hello， 回复 hello too")
    case "ok" => println("收到ok， 回复 ok too")
    case "exit"=>{
      println("接收到退出指令")
      context.stop(self)
      context.system.terminate()
    }
    case _ => println("啥都没收到")
  }
}

object SayHelloActorDemo {
  private val actoryFactory = ActorSystem("actoryFactory")
  private val sayHelloActorDemo: ActorRef = actoryFactory.actorOf(Props[SayHelloActor],"sayHelloActorDemo")

  def main(args: Array[String]): Unit = {
    sayHelloActorDemo ! "hello"
    sayHelloActorDemo ! "ok"
    sayHelloActorDemo ! "ok00"
    sayHelloActorDemo ! "exit"
  }
}
