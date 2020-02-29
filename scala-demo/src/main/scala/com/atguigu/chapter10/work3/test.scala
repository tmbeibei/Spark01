package com.atguigu.chapter10.work3

/**
 * @author tianmin
 * @date 2020/2/21 0021
 * @notes
 */
object test {
  def main(args: Array[String]): Unit = {
    //给定一个整数数组，产出一个新的数组，包含原数组中的所有正值，以原有顺序排列，之后的元素是所有零或负值，以原有顺序排列。
    var arr = Array(1,6,2,-1,9,0,-2,44,12)
    var arr1 = new Array[Int](arr.length)
    // 1 取出所有的正数
    var index = -1;
    for(i <- 0 until arr.length){
      if(arr(i) > 0 ){
        index += 1
        arr1(index) = arr(i)
      }
    }


    // 2 取出所有的零或者负数
    for(i <- 0 until arr.length){
      if(arr(i) <= 0 ){
        index += 1
        arr1(index) = arr(i)
      }
    }

    for(item <- arr1){
      println(item)
    }

  }
}
