package com.atguigu.chapter07.work2

/**
 * @author tianmin
 * @date 2020/2/20 0020
 * @notes
 */
class Person {
  var name:String = _

  def cry(): Unit ={
    println("你是妖怪")
  }
}

object Person{
  var sex:Boolean = true

  def sayHi(): Unit ={
    println("object Person sayHi")
  }
}
