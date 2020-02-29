package com.atguigu.akkn.actor

import akka.actor.Actor
import akka.actor.Actor.Receive



/**
 * @author tianmin
 * @date 2020/2/23 0023
 * @notes
 */
class BActor extends Actor{
  override def receive: Receive = {
    case "我打"=>{
      println("BActor(乔峰) 挺猛 看我降龙十八掌")
      Thread.sleep(500)
      sender() ! "我打"
    }
  }
}
