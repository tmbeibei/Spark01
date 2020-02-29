package com.atguigu.chapter01

/**
 * @author tianmin
 * @date 2020/2/18 0018
 * @notes
 */
object PrintDemo {
  def main(args: Array[String]): Unit = {
    var str1:String = "Hello"
    var str2:String = " wolrd"
    println(str1 + str2)
    var name:String = "tom"
    var age:Int = 30
    var sal:Float = 5800.6f
    var height:Double = 180.56
    printf("姓名:%s \t年龄%d \t 薪水%.2f \t 身高%.3f",name,age,sal,height)
    println(s"\n个人信息：姓名:$name\n 年龄:${age + 5}\n 薪水:${sal*10}\n 身高:$height\n")

  }

}
