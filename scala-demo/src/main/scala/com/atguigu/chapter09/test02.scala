package com.atguigu.chapter09

/**
 * @author tianmin
 * @date 2020/2/20 0020
 * @notes
 */
object test02 {
  def main(args: Array[String]): Unit = {
    implicit var str1:String = "jack"
    implicit var str2:String = "tom"
  }

  def hello(implicit name:String): Unit ={
    println(name + " hello")
  }
}
