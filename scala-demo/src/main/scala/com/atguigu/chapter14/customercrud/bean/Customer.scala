package com.atguigu.chapter14.customercrud.bean

/**
 * @author tianmin
 * @date 2020/2/23 0023
 * @notes
 */
class Customer {
  var id:Int = _
  var name:String = _
  var gender:Char = _
  var age:Int = _
  var tel:String = _
  var email:String = _

  /**
   * 构造参数
   * @param id
   * @param name
   * @param gender
   * @param age
   * @param tel
   * @param email
   */
  def this(id:Int,name:String,gender:Char,age:Int,tel:String,email:String){
    this
    this.id = id
    this.name = name
    this.gender = gender
    this.age =age
    this.tel = tel
    this.email = email
  }

  /**
   * 构造参数
   * @param name
   * @param gender
   * @param age
   * @param tel
   * @param email
   */
  def this(name:String,gender:Char,age:Int,tel:String,email:String){
    this
    this.name = name
    this.gender = gender
    this.age =age
    this.tel = tel
    this.email = email
  }

  override def toString: String = {
    this.id + "\t\t" + this.name + "\t\t" + this.gender + "\t\t" + this.age + "\t\t" + this.tel + "\t\t" + this.email
  }
}
