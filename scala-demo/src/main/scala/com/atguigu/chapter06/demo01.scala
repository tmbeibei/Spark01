package com.atguigu.chapter06

import scala.reflect.internal.util.StringOps

/**
 * @author tianmin
 * @date 2020/2/19 0019
 * @notes
 */
object demo01 {
  def main(args: Array[String]): Unit = {
    /*val name = {}
    println(name)
    println(name.isInstanceOf[Unit])*/
    //exec3()
    //countdown(8)
    //println("res:" + exec5("Hello"))
    println("res:" + exec6("Hello"))
  }



  //同样是解决前一个练习的问题，请用StringOps的foreach方式解决。
  def exec6(str:String): Long ={
    var sum = 1L
    str.foreach(item => sum *= item.toLong)
    sum
  }

  def exec5(str:String): Long ={
    var sum = 1L
    for(i <- 0 until  str.length ){
      sum *= str.codePointAt(i)
      //println(str.charAt(i) + " " + str.codePointAt(i) )
    }
    sum
  }

  def exec3(): Unit ={
    for(i <- Range(10,-1,-1)){
      println(i)
    }
  }

  def countdown(n:Int): Unit ={
    for(i <- Range(n,-1,-1)){
      println(i)
    }
  }
}
