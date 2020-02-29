package com.atguigu.chapter06

/**
 * @author tianmin
 * @date 2020/2/19 0019
 * @notes
 */
class TravelPerson {
  var name:String = _
  var age:Int = _

  def calPrice(): Unit ={
    if(age > 18){
      println(s"$name 的年龄为 $age,门票价格为:20")
    }else{
      println(s"$name 的年龄为 $age,门票免费")
    }
  }
}
