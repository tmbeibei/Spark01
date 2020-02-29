package com.atguigu.chapter03

/**
 * @author tianmin
 * @date 2020/2/18 0018
 * @notes
 */
object Test1 {
  def main(args: Array[String]): Unit = {
    val num1 = 50
    val num2 = 80
    val num3 = 60

//    num1 = num1 + num2
    ////    num2 = num1 - num2
    ////    num1 = num1 - num2
    ////    println(s"num1: $num1,num2:$num2")

    //println(max(num1,num2))
    val temp = if(num1 > num2) num1 else num2
    val max = if(temp > num3) temp else num3
    println(s"max:$max")
  }

  def max(num1:Int,num2:Int): Int ={
    if(num1 > num2) num1 else num2
  }
}
