package com.atguigu.chapter08.work5

/**
 * @author tianmin
 * @date 2020/2/20 0020
 * @notes
 */
object test01 {
  def main(args: Array[String]): Unit = {
    // 1 普通类 动态混入
    val mysql = new Mysql with Mytarit
    mysql.insert(800)

    // 2 空抽象类混入
    val oracle = new Oracle with Mytarit
    oracle.insert(900)

    // 3 抽象类动态混入
    val mydb = new MangDB with Mytarit {
      override def sayHello(): Unit = {
        println(" hello ~~")
      }
    }

    mydb.insert(100)
    mydb.sayHello()
  }
}
