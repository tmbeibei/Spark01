package com.atguigu.akkn.actor

import akka.actor.{Actor, ActorRef}

/**
 * @author tianmin
 * @date 2020/2/23 0023
 * @notes
 */
class AActor(actorRef:ActorRef) extends Actor{

  val bActorRref:ActorRef = actorRef

  override def receive: Receive = {
    case "start"=>{
      println("出招了 Start，OK!")
      self ! "我打"
    }
    case "我打"=>{
      println("AActor(黄飞鸿):佛山无影脚")
      Thread.sleep(500)
      bActorRref ! "我打"
    }
  }
}
