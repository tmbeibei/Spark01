package com.atguigu.chapter08.work3

/**
 * @author tianmin
 * @date 2020/2/20 0020
 * @notes
 */
class Point(inP1:Int,inP2:Int) {
  var p1:Int = inP1
  var p2:Int = inP2
}

object Point{
  def apply(inP1: Int, inP2: Int): Point = new Point(inP1, inP2)
}
