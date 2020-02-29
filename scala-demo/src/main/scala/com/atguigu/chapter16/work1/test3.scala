package com.atguigu.chapter16.work1

/**
 * @author tianmin
 * @date 2020/2/23 0023
 * @notes
 */
object test3 {
  def main(args: Array[String]): Unit = {
    var lines = List("atguigu atguigu han hello", "atguigu tian tian min aaa aaa bbb aaa ccc zs li zs", "zs li tian")

    lines.flatMap(item => item.split(" ")) //扁平化
      .map((_, 1)) // 组成(item,1)的二元祖
      .groupBy(_._1) // 按照item分组
      .map(x => (x._1, x._2.size)) //组成(item,size)的二元祖
      .toList //转成List
      .sortBy(x => x._2) // 排序
      .reverse // 反转
      .map(item => println(item._1 + "\t" + item._2))

    //println(res1)
  }

  def fun(tup: (String, Int)) = {
    println(tup._1 + "\t" + tup._2)
  }

}
