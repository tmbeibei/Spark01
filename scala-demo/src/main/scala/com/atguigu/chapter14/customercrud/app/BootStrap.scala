package com.atguigu.chapter14.customercrud.app

import com.atguigu.chapter14.customercrud.view.CustomerView

/**
 * @author tianmin
 * @date 2020/2/23 0023
 * @notes
 */
object BootStrap {
  def main(args: Array[String]): Unit = {
    new CustomerView().view()
  }
}
