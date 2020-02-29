package com.atguigu.chapter11.works02

/**
 * @author tianmin
 * @date 2020/2/21 0021
 * @notes
 */
object test2 {
  def main(args: Array[String]): Unit = {
    var lst = List("tom","jack","lusi","At","Atom")
    val lst2 = lst.filterNot(withA)
   // println(lst2)

    val lst3 = List(5.0,3,7,9,88,23,3,45)
    val sumcount = lst3.reduceLeft(sum)
    println("合计:" + sumcount)
    val res = lst3.reduceLeft(min)
    println("最小值:" + res)
  }

  def sum(num1:Double,num2:Double): Double ={
    println("被调用了")
    num1 + num2
  }

  def min(num1:Double,num2:Double): Double ={
    Math.min(num1,num2)
  }

  def withA(str:String): Boolean ={
    str.startsWith("A")
  }

  def toUpper(str:String): String ={
    str.toUpperCase
  }
}
