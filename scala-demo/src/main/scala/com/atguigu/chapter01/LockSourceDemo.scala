package com.atguigu.chapter01

/**
 * @author tianmin
 * @date 2020/2/18 0018
 * @notes
 */
object LockSourceDemo {
  def main(args: Array[String]): Unit = {
    var arr = new Array[Int](10)

    for (item <- arr) {
      println(item)
    }
  }

}
