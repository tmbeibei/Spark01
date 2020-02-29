package com.atguigu.chapter14.customercrud.service

import com.atguigu.chapter14.customercrud.bean.Customer

import scala.collection.mutable.ListBuffer
import scala.util.control.Breaks._

/**
 * @author tianmin
 * @date 2020/2/23 0023
 * @notes
 */
class CustomerService {
  //数据仓库
  private val customers = ListBuffer[Customer](
    new Customer(1, "张三", '男', 30, "18820972489", "zs@163.com"),
    new Customer(2, "赵敏", '女', 45, "18820972401", "zm@163.com"))

  private var customerNum = 2

  /**
   * 返回客户集合
   *
   * @return
   */
  def list(): ListBuffer[Customer] = {
    customers
  }

  /**
   * 添加客户
   *
   * @param customer
   * @return
   */
  def add(customer: Customer): Boolean = {
    // 1 修改客户ID
    customerNum += 1
    customer.id = customerNum

    // 2 添加客户
    customers += customer
    true
  }

  /**
   * 删除
   * @param id
   */
  def del(id: Int): Boolean = {
    val index = getIndexById(id)
    if(index != -1){
      customers.remove(index)
      true
    }else{
      false
    }
  }

  /**
   * 根据客户ID查找index
   * @param id
   */
  def getIndexById(id: Int): Int = {
    var index = -1
    breakable {
      for (i <- 0 until customers.length) {
        if (customers(i) == id) {
          index = i
          break()
        }
      }
    }

    index
  }

  /**
   * 根据ID查询客户
   * @param id
   * @return
   */
  def getCustomerById(id:Int): Customer ={
    var customer = new Customer
    val index = getIndexById(id)
    if(index != -1){
      customer = customers(index)
    }
    customer
  }

  /**
   * 更新
   * @param customer
   * @return
   */
  def update(customer: Customer): Boolean ={
    val id = customer.id
    val index = getIndexById(id)
    if(index != -1){
      customers -= customer
      true
    }else{
      false
    }
  }
}
