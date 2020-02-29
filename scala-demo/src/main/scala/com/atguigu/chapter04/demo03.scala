package com.atguigu.chapter04

/**
 * @author tianmin
 * @date 2020/2/18 0018
 * @notes
 */
object demo03 {
  def main(args: Array[String]): Unit = {
    var sumMoney = 100000D //总金额
    val rate = 0.05  //大于50000的时候过路费
    val takeMoney = 1000D //小于50000的时候的过路费
    val middleMoney = 50000D
    var rute = 0 //关卡
    while (sumMoney > takeMoney){
      if(sumMoney > middleMoney){
        sumMoney -= sumMoney * rate;
      }else{
        sumMoney -= takeMoney
      }
      rute +=1
    }

    println(s"总共进过了此路口$rute，还剩下${sumMoney}")
  }
}
