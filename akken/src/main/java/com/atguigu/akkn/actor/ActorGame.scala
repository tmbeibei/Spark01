package com.atguigu.akkn.actor

import akka.actor.{ActorSystem, Props}

/**
 * @author tianmin
 * @date 2020/2/23 0023
 * @notes
 */
object ActorGame {
  def main(args: Array[String]): Unit = {
    val factory = ActorSystem("factory")

    // 1 创建BActor
    val bActor = factory.actorOf(Props[BActor],"BActor")


    // 2 创建AActor
    val aActor = factory.actorOf(Props(new AActor(bActor)),"AActor")

    // 3 启动
    aActor ! "start"
  }

}
