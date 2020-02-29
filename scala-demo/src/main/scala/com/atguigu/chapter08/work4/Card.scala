package com.atguigu.chapter08.work4

/**
 * @author tianmin
 * @date 2020/2/20 0020
 * @notes
 */
class Card {

}

object Card{
  val black:String = "♠"
  val red:String = "♥"
  val club:String = "♣"
  val flower:String = "♦"

  override def toString: String = {
    black + "," + red + "," + club + "," + flower
  }

  def checkRed(color:String): Boolean ={
    if(this.red.equals(color)){
      true
    }else{
      false
    }
  }
}
