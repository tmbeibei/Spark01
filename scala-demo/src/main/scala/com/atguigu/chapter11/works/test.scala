package com.atguigu.chapter11.works

/**
 * @author tianmin
 * @date 2020/2/21 0021
 * @notes
 */
object test {
  def main(args: Array[String]): Unit = {
    val list = List(1,3,5)
    val list2 = add2(list)
    println(list2)
  }

  def add2(list:List[Int]): List[Int] ={
    var list2 = List[Int]()
    for(item <- list){
      list2 = list2 :+ item * 2
    }
    list2
  }
}
