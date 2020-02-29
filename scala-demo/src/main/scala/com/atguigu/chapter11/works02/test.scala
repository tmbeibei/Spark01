package com.atguigu.chapter11.works02

/**
 * @author tianmin
 * @date 2020/2/21 0021
 * @notes
 */
object test {
  def main(args: Array[String]): Unit = {
    val mylist = MyList()
    println(mylist)
    val myList2 = mylist.map(multify)
    println(myList2)

  }

  def multify(num:Int): Int ={
    num * 3
  }
}
