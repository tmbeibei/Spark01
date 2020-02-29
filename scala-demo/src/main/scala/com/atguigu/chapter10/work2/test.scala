package com.atguigu.chapter10.work2

/**
 * @author tianmin
 * @date 2020/2/21 0021
 * @notes
 */
object test {
  def main(args: Array[String]): Unit = {
    var arr1 = Array(1,2,3,4,5,88)
    for(i <- 0 until arr1.length - 1 if i %2 == 0){
      //println(arr1(i))
      val temp = arr1(i)
      arr1(i) = arr1(i + 1)
      arr1(i + 1) = temp
    }

    // 打印arr1
    for(item <- arr1){
      print(item + "\t")
    }
  }
}
