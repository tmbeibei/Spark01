package com.atguigu.chapter12.work4

/**
 * @author tianmin
 * @date 2020/2/22 0022
 * @notes
 */
object test {
  def main(args: Array[String]): Unit = {
    var arrs = Array(Dollar(1000.0), Currency(1000.0, "RMB"), NoAmount)
    for (item <- arrs) {
      val result = item match {
        case Dollar(v) => "$" + v
        case Currency(v, u) => v + " " + u
        case NoAmount => "nothing"
      }
      println("res:" + result)
    }
  }
}

abstract class Amount {

}

case class Dollar(value: Double) extends Amount {

}

case class Currency(value: Double, unit: String) extends Amount {

}

case object NoAmount extends Amount
