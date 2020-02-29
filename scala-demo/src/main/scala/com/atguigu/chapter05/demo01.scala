package com.atguigu.chapter05

/**
 * @author tianmin
 * @date 2020/2/18 0018
 * @notes
 */
object demo01 {
  def main(args: Array[String]): Unit = {
    //println("res:" + fun1(30))
    println("res:" + fun2(1))
  }

  /**
   * day10 1
   * day9  (day10 + 1) * 2   = 4
   * day8  (day9 + 1) * 2 = 10
   * @param num
   * @return
   */
  def fun2(num:Int): Int ={
    if(num == 10){
      1
    }else{
      (fun2(num + 1) + 1) * 2
    }
  }

  def fun1(num:Int): Int ={
    //1	1	2	3	5	8	13
    if(num == 1 || num == 2){
      return 1
    }else{

      fun1(num - 1) + fun1(num -2)
    }
  }
}
