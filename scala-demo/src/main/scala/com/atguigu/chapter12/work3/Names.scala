package com.atguigu.chapter12.work3

/**
 * @author tianmin
 * @date 2020/2/22 0022
 * @notes
 */
class Names {

}

object Names{
  def unapplySeq(str: String): Option[Seq[String]] = {
    if(str.contains(",")){
      println("调用了")
      Some(str.split(","))
    }else{
      None
    }
  }
}
