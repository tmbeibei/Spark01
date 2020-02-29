package com.atguigu.chapter06

/**
 * @author tianmin
 * @date 2020/2/19 0019
 * @notes
 */
object CatTest {
  def main(args: Array[String]): Unit = {
    val cat = new Cat
    cat.name = "小白"
    cat.age = 3
    cat.color = "白色"

    println("ok--")
  }
}

class Cat {
  var name: String = _
  var age: Int = _
  var color: String = _

}
