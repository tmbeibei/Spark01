package com.atguigu.chapter11.work03

/**
 * @author tianmin
 * @date 2020/2/21 0021
 * @notes
 */
object test06 {
  def main(args: Array[String]): Unit = {
    //(1 to 10).par.foreach(println)
    val res1 = (0 to 100).map{case _ => Thread.currentThread().getName}.distinct
    val res2 = (0 to 100).par.map{case _ => Thread.currentThread().getName}.distinct
    println(res1)
    println("==========================================")
    println(res2)
  }
}
