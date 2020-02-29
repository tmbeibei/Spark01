package com.atguigu.chapter07.work1

/**
 * @author tianmin
 * @date 2020/2/20 0020
 * @notes
 */
class Common(inName:String,inSalary:Double)  extends Employee(inName,inSalary) {
  def  work(): Unit ={
    println("普通员工在工作")
  }

  override def getAnnual(): Double = {
    salary * 12  + 10
  }
}
