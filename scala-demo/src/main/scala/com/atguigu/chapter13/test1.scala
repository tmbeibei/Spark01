package com.atguigu.chapter13

/**
 * @author tianmin
 * @date 2020/2/22 0022
 * @notes
 */
object test1 {
  def main(args: Array[String]): Unit = {
    val lst = List(1,2,3,4,"abc")
    val lst2 = lst.filter(guolv).map(add)
    println(lst2)

  }

  def guolv(value:Any): Boolean ={
    if(value.isInstanceOf[Int]){
      true
    }else{
      false
    }
  }

  def add(value:Any): Int ={
    if(value.isInstanceOf[Int]){
      value.asInstanceOf[Int] + 1
    }else{
      0
    }
  }

}
