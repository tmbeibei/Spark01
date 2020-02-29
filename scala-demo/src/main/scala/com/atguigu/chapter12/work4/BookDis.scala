package com.atguigu.chapter12.work4

/**
 * @author tianmin
 * @date 2020/2/22 0022
 * @notes
 */
object BookDis {
  def main(args: Array[String]): Unit = {
    val sale = Bundle("书籍", 10, Book("漫画", 40), Bundle("文学作品", 20, Book("阳关", 80), Book("围城", 30)))

    val res = sale match {
      case Bundle(_, _, Book(desc, _), _*) => desc
      case _ => "none"
    }
    println("res=" + res + "========================")

    val result = sale match {
      case Bundle(_, _, art@Book(_, _), rest@_*) => (art, rest)
    }
    println(result)
    println(result._1)
    println(result._2)

    println("最终结果:" + price(sale))
  }

  def price(it: Item): Double = {
    it match {
      case Book(_, p) => p
      case Bundle(_,disc,its @ _*)=>its.map(price).sum - disc
    }
  }
}
