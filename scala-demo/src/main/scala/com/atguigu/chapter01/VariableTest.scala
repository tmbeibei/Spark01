package com.atguigu.chapter01

/**
 * @author tianmin
 * @date 2020/2/18 0018
 * @notes
 */
object VariableTest {
  def main(args: Array[String]): Unit = {
    var age:Int = 30
    age = 40

    val name:String = "tom"
    //name = "lusi"

    val dog = new Dog
    dog.name="xh"
    dog.age=90
  }

  class Dog{
    var name:String = ""
    var age:Int = 0
  }
}
