package com.atguigu.akka.spark.common

/**
 * @author tianmin
 * @date 2020/2/23 0023
 * @notes 保存到Master的HashMap中
 */
class WorkerInfo(val id: String, cup: Int, ram: Int) {
  var lastHeartBeat: Long = System.currentTimeMillis()
}
