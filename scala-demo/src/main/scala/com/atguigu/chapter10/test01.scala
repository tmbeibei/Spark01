package com.atguigu.chapter10

/**
 * @author tianmin
 * @date 2020/2/21 0021
 * @notes
 */
object test01 {
  def main(args: Array[String]): Unit = {
/*    var arr = new Array[Int](4)
    println("长度:" + arr.length)
    for (item <- arr) {
      println(item)
    }*/

    var arr2 = Array(1,3,"xx")

   for(index <- 0 until arr2.length){
     printf("array[%d]=%s\t",index,arr2(index))
   }
  }
}
