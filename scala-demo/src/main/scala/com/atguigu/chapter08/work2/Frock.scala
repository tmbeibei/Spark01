package com.atguigu.chapter08.work2

import scala.beans.BeanProperty

/**
 * @author tianmin
 * @date 2020/2/20 0020
 * @notes
 */
class Frock() {
  @BeanProperty var serialNumber:Int = Frock.getNextNum()

}

object Frock{
  //起始序号
  private var currentNum:Int = 100000

  /**
   * 当前序号+100，并且返回
   * @return
   */
  def getNextNum(): Int ={
    currentNum +=100
    currentNum
  }
}
