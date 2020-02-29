package com.atguigu.chapter11.work03

/**
 * @author tianmin
 * @date 2020/2/21 0021
 * @notes
 */
object test05 {
  def main(args: Array[String]): Unit = {
    //val lst = (1 to 100).filter(flg);
    val lst = (1 to 100).view.filter(flg)
    println(lst)
    for(item <- lst){
      println(item)
    }
  }

  /**
   * 本身和反转之后的比较，看是否相等
   * @param num
   * @return
   */
  def flg(num:Int): Boolean ={
    num.toString.equals(num.toString.reverse)
  }


}
