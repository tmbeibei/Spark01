package com.atguigu.chapter08.work1

/**
 * @author tianmin
 * @date 2020/2/20 0020
 * @notes
 */
object test01 {
  def main(args: Array[String]): Unit = {
    val children01 = new Children("白骨精")
    val children02 = new Children("蜘蛛精")
    val children03 = new Children("红孩儿")

    Children.joinChildrenGame(children01)
    Children.joinChildrenGame(children02)
    Children.joinChildrenGame(children03)
    Children.showTotal()
  }
}
