package com.atguigu.chapter14.customercrud.view

import com.atguigu.chapter14.customercrud.bean.Customer
import com.atguigu.chapter14.customercrud.service.CustomerService

import scala.io.StdIn

/**
 * @author tianmin
 * @date 2020/2/23 0023
 * @notes
 */
class CustomerView {
  private val service = new CustomerService
  /*
  -----------------客户信息管理软件-----------------

                            1 添 加 客 户
                            2 修 改 客 户
                            3 删 除 客 户
                            4 客 户 列 表
                            5 退       出

                            请选择(1-5)：_

   */
  def view(): Unit ={
    var index = -1
    var loop = true
    do{

      println("-----------------客户信息管理软件-----------------")
      println("")
      println("                 1 添 加 客 户")
      println("                 2 修 改 客 户")
      println("                 3 删 除 客 户")
      println("                 4 客 户 列 表")
      println("                 5 退       出")
      println("")
      println("                 请选择(1-5)：_")
      index = StdIn.readInt()
      //退出
        if(index == 5){
          loop = false
        }

      index match {
        case 1 => this.add()
        case 2 => println("2 修 改 客 户")
        case 3 => println("3 删 除 客 户")
        case 4 => this.list()
        case _ =>
      }

    }while(loop)

  }
/*
---------------------------客户列表---------------------------
编号  姓名       性别    年龄   电话            邮箱
 1    张三       男      30     010-56253825   abc@email.com
 2    李四       女      23     010-56253825    lisi@ibm.com
 3    王芳       女      26     010-56253825   wang@163.com
-------------------------客户列表完成-------------------------

 */
  def list(): Unit ={
    println()
    println("---------------------------客户列表---------------------------")
    println("编号\t\t姓名\t\t性别\t\t年龄\t\t电话\t\t邮箱")
    val customers = service.list()
    for(item <- customers){
      println(item)
    }
    println("-------------------------客户列表完成-------------------------")
    println()
  }

  /*
  ---------------------添加客户---------------------
姓名：张三
性别：男
年龄：30
电话：010-56253825
邮箱：zhang@abc.com
---------------------添加完成---------------------

   */
  def add(): Unit ={
    println()
    println("---------------------添加客户---------------------")
    println("姓名：")
    val name = StdIn.readLine()
    println("性别：")
    val gender = StdIn.readChar()
    println("年龄：")
    val age = StdIn.readInt()
    println("电话：")
    val tel = StdIn.readLine()
    println("邮箱：")
    val email = StdIn.readLine()
    val customer = new Customer(name,gender,age,tel,email)
    if(service.add(customer)){
      println("添加成功")
      return
    }
    println("添加失败")
  }

  /**
   * ---------------------删除客户---------------------
   * 请选择待删除客户编号(-1退出)：1
   * 确认是否删除(Y/N)：y
   * ---------------------删除完成---------------------
   */
  def del(): Unit ={
    var id = -1
    println()
    println("---------------------删除客户---------------------")
    println("请选择待删除客户编号(-1退出)：1")


    id = StdIn.readChar()
    if(id == -1){
      println("删除没有完成")
      return
    }

    println("确认是否删除(Y/N)：y")
    var chooise = StdIn.readChar()


  }

}
