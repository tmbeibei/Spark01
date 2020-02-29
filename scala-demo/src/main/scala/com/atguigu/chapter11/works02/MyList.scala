package com.atguigu.chapter11.works02

/**
 * @author tianmin
 * @date 2020/2/21 0021
 * @notes
 */
class MyList {
  val lst = List(1,2,6)
  var lst2 = List[Int]()

  def map(f:Int => Int): List[Int] ={
    // 1 遍历lst
    for(item<- lst){
      // 2 遍历过程中调用f方法
      lst2 = lst2 :+ f(item)
    }
    lst2
  }
}

object MyList{
  def apply(): MyList = new MyList()
}