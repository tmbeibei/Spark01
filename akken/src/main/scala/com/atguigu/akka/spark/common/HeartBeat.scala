package com.atguigu.akka.spark.common

/**
 * @author tianmin
 * @date 2020/2/23 0023
 * @notes worker 每隔一定时间由定时器触发，向Master发送心跳消息
 */
case class HeartBeat(id:String)
