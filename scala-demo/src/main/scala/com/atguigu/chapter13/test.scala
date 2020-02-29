package com.atguigu.chapter13

/**
 * @author tianmin
 * @date 2020/2/22 0022
 * @notes
 */
object test {
  def main(args: Array[String]): Unit = {
    val lst = List(1,3,5,9)
    println(lst.reduceLeft(sum1))

    println(num3(8)(10))
  }

  def sum1(num1:Int,num2:Int): Int ={
    num1 + num2
  }

  def sum2(num1:Int) ={
    (num2:Int)=>{
      num1 + num2
    }

  }

  def num3(x:Int)(y:Int) ={
    x*y
  }

  def num3(str1:String) ={
    (str2:String)=>{
      str1.toLowerCase.equals(str2.toLowerCase)
    }
  }
}
