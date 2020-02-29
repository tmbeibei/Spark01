package com.atguigu.chapter07.work1

/**
 * @author tianmin
 * @date 2020/2/20 0020
 * @notes
 */
class Employee(inName:String,inSalary:Double) {
  var name:String = inName
  var salary:Double = inSalary

  /**
   * 一年的工资
   * @return
   */
  def getAnnual(): Double ={
    salary * 12
  }
}
