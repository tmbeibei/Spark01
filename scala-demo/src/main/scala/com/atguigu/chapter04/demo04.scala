package com.atguigu.chapter04

/**
 * @author tianmin
 * @date 2020/2/18 0018
 * @notes
 */
object demo04 {
  def main(args: Array[String]): Unit = {
    println("res:" + sub(50,20,'0'))
  }

  def sub(num1:Int,num2:Int,oper:Char)={
    if(oper.equals('-')){
      num1 - num2
    }else if(oper.equals('+')){
      num1 + num2
    }else{
      null
    }
  }
}
