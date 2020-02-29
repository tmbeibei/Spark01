package com.atguigu.chapter16.work1

/**
 * @author tianmin
 * @date 2020/2/23 0023
 * @notes
 */
object test {
  def main(args: Array[String]): Unit = {
    /*val t1 = (10, 20)
    val t2 = swap(t1)
    println(t2)*/

    /*val arr = Array(1, 3)
    val arr2 = swap(arr)
    for (item <- arr2) {
      println(item)
    }*/

    val lst = List(Option(1),Option(2),Option(5),None,Some(5))

    println(fun1(lst))

    /*val arr = Array(3,3,55,99,124,23,2)
    println(myMax(arr))*/

    /*val res:BigInt = (1 to 10).reduceLeft((num1,num2)=>num1*num2)
    println("res=" + res)*/
    /* val arr = Array(2, 8, 3)
     val arr2 = arr ++ Array(1, 5)
     println(arr2)
     for (item <- arr2) {
       println(item)
     }*/
  }

  /*
    利用模式匹配，编写一个swap函数，接受一个整数的对偶，返回对偶的两个组成部件互换位置的新对偶
     */
  def swap(t1: (Int, Int)) = {
    t1 match {
      case t1 => {
        (t1._2, t1._1)
      }
    }
  }

  /*
  2、利用模式匹配，编写一个swap函数，交换数组中的前两个元素的位置，前提条件是数组长度至少为2
   */
  def swap(arr: Array[Int]) = {
    arr match {
      case Array(first, second, rest@_*) => {
        Array(second, first) ++ rest
      }
      case _ => arr
    }
  }

  /*
  3、编写一个函数，计算List[Option[Int]]中所有非None值之和。不得使用match语句。
   */
   def fun1(lst:List[Option[Int]]) ={
     lst.map(_.getOrElse(0)).sum
   }

  /*
  3、如何用reduceLeft得到数组Array(1,333,4,6,4,4,9,32,6,9,0,2)中的最大元素?
   */

  def myMax(arr: Array[Int]): Int = {
    arr.reduceLeft((num1, num2) => Math.max(num1, num2))
  }

}
