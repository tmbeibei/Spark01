package com.atguigu.chapter04

/**
 * @author tianmin
 * @date 2020/2/18 0018
 * @notes
 */
object demo01 {
  def main(args: Array[String]): Unit = {
    // 1 打印1-100之间所有是9的倍数的整数的个数和总和
    var num = 0
    var sum = 0
    for(item <- 1 to 100){
      if(item % 9 ==0 ){
        num += 1
        sum += item;
      }
    }

    println(s"个数:$num,总和:$sum")
  }
}
