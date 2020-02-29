package com.atguigu.chapter11.work03

import scala.collection.mutable

/**
 * @author tianmin
 * @date 2020/2/21 0021
 * @notes
 */
object tgest {
  def main(args: Array[String]): Unit = {
    val words = "AABBBDeCBCC"
    val map = mutable.Map[Char,Int]()
    words.foldLeft(map)(charCount);
    println("res:" + map)
  }


   def charCount(map:mutable.Map[Char,Int],char:Char): mutable.Map[Char,Int] ={
     map += (char -> (map.getOrElse(char,0) + 1 ))
   }

  def flg(map:mutable.HashMap[Char,Int],c:Char): mutable.HashMap[Char,Int] ={
    if(map.contains(c)){
      map(c) += 1
    }else{
      map(c) = 1
    }
    map
  }
}
