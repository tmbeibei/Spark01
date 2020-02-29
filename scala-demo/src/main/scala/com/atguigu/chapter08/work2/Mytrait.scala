package com.atguigu.chapter08.work2

/**
 * @author tianmin
 * @date 2020/2/20 0020
 * @notes
 */
trait Mytrait {
  def sayHi()

  def cry(): Unit ={
    println("哇哇叫：接口里面实现的")
  }
}
