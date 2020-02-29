package com.atguigu.chapter13

/**
 * @author tianmin
 * @date 2020/2/22 0022
 * @notes
 */
object test2 {
  def main(args: Array[String]): Unit = {
    val lst = List(1,2,3,4,"abc")
    println(lst.map(addOne))
  }

  def addOne(value:Any): Any ={
    value match {
      case a:Int => a + 1
      case _=>
    }
  }

}
