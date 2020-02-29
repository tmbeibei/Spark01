package com.atguigu.chapter07.work1

import scala.beans.BeanProperty

/**
 * @author tianmin
 * @date 2020/2/20 0020
 * @notes
 */
class Cylinder extends Cicle {
  @BeanProperty var len:Double = 1

  /**
   * 计算圆柱的体积
   * @return
   */
  def findVolume(): Double ={
    findArea() * len
  }
}
