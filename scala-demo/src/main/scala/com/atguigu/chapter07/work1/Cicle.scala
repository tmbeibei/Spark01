package com.atguigu.chapter07.work1

import scala.beans.BeanProperty

/**
 * @author tianmin
 * @date 2020/2/20 0020
 * @notes
 */
class Cicle() {
  @BeanProperty var radius:Double = 1

  /**
   * 计算圆的面积
   * @return
   */
  def findArea(): Double ={
    Math.PI * radius * radius
  }
}
