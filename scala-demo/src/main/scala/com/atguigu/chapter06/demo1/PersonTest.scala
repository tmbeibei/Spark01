package com.atguigu.chapter06.demo1

/**
 * @author tianmin
 * @date 2020/2/19 0019
 * @notes
 */
object PersonTest {
  def main(args: Array[String]): Unit = {
    val person = new Person("zs")
    println(person.inName + "\t" + person.name )
  }
}

class Person(val inName:String) {
  var name :String = inName
}
