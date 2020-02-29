package com.atguigu.chapter12.work2

/**
 * @author tianmin
 * @date 2020/2/22 0022
 * @notes
 */
class Square {

}

object Square{
  /**
   * 对象提取器
   * @param value
   * @return
   */
  def unapply(value: Double): Option[Double] = {
    Some(Math.sqrt(value))
  }

  def apply(z: Double): Double = z * z
}
