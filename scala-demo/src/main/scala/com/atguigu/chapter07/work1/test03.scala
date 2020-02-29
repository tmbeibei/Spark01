package com.atguigu.chapter07.work1

import com.atguigu.chapter01.Common

/**
 * @author tianmin
 * @date 2020/2/20 0020
 * @notes
 */
object test03 {
  def main(args: Array[String]): Unit = {
    val emp1:Employee = new Common("张三",1000)
    showEmpAnnual(emp1)
    testWork(emp1)

    println("**************************")

    val emp2:Employee = new Manager("李四",2000,500)

    showEmpAnnual(emp2)
    testWork(emp2)
  }

  def showEmpAnnual(employee: Employee): Unit ={
    println(s"姓名:${employee.name}\t年工资:${employee.getAnnual()}")
  }

  def  testWork(employee: Employee): Unit ={
    if(employee.isInstanceOf[Common]){
      employee.asInstanceOf[Common].work()
    }else if(employee.isInstanceOf[Manager]){
      employee.asInstanceOf[Manager].manage()
    }
  }
}
