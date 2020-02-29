package com.atguigu.chapter07.work1

/**
 * @author tianmin
 * @date 2020/2/20 0020
 * @notes
 */
class Manager(inName:String,inSalary:Double,inBonus:Double) extends Employee(inName, inSalary) {
  var bonus:Double = inBonus

  def manage(): Unit ={
    println("经理的管理方法")
  }

  override def getAnnual(): Double = {
    salary * 12 + bonus
  }
}
