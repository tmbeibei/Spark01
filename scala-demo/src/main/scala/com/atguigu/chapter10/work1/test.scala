package com.atguigu.chapter10.work1

import java.util.Random

import scala.collection.mutable.ListBuffer

/**
 * @author tianmin
 * @date 2020/2/21 0021
 * @notes
 */
object test {
  def main(args: Array[String]): Unit = {
    var i = 0
    var list = ListBuffer[Int]()
    while (i < 5){
      list += getRandom(5)

      i+=1
    }

    println(list)
    // 遍历
    for(item <- list){
      println(item)
    }
  }

  def getRandom(num:Int): Int ={
    (Math.random() * (num + 1)).toInt
   // val random =  new Random()
    //random.nextInt(num)
  }
}
