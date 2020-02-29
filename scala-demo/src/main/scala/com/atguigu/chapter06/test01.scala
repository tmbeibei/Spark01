package com.atguigu.chapter06

import scala.io.StdIn
import scala.util.control.Breaks

/**
 * @author tianmin
 * @date 2020/2/19 0019
 * @notes
 */
object test01 {
  def main(args: Array[String]): Unit = {
    var flg: String = ""
    val person = new TravelPerson

    //创建一个break对象
    val loop = new Breaks
    loop.breakable {
      while (true) {
        println("请输入姓名:")
        var name = StdIn.readLine()

        //退出
        if (name.equals("n")) {
          loop.break()
        }

        person.name = name

        println("请输入年龄:")
        person.age = StdIn.readInt()

        //打印
        person.calPrice()
      }
    }
  }
}
