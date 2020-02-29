package com.atguigu.chapter05

/**
 * @author tianmin
 * @date 2020/2/19 0019
 * @notes
 */
object demo02 {
  def main(args: Array[String]): Unit = {
    //printJzt(8)
    printXfb()
  }

  def printJzt(num:Int){
    for(i <- 1 to num){
      // 1 打印空格
      for(row <- 1 to num - i){
        print(" ")
      }

      // 2 打印*
      for(row <- 1 to 2*i - 1){
        print("*")
      }

      // 3 换行
      println()
    }
  }

  def printXfb(){
    for(row <- 1 to 9){
      for(column <- 1 to row){
        print(s"$column * $row = ${row * column}\t")
      }
      println()
    }
  }
}
