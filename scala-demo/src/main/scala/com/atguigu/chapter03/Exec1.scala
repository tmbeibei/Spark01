package com.atguigu.chapter03

/**
 * @author tianmin
 * @date 2020/2/18 0018
 * @notes
 */
object Exec1 {
  def main(args: Array[String]): Unit = {
    // 1 判断是否是闰年
    val year = 2020
    if((year % 4 == 0 && year %100 != 0) || year % 400 == 0){
      println(s"$year 是闰年")
    }else{
      println(s"$year 不是是闰年")
    }
  }
}
