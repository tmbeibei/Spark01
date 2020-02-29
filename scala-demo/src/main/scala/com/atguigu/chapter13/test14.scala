package com.atguigu.chapter13

/**
 * @author tianmin
 * @date 2020/2/22 0022
 * @notes
 */
object test14 {
  def main(args: Array[String]): Unit = {
    val str="hello world"
    println(resver2(str))
  }

  def resver2(str:String): String ={
    if(str.length == 1){
      str
    }else{
      resver2(str.tail) + str.head
    }
  }

}
