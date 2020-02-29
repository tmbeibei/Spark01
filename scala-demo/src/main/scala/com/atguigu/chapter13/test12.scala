package com.atguigu.chapter13

/**
 * @author tianmin
 * @date 2020/2/22 0022
 * @notes
 */
object test12 {
  def main(args: Array[String]): Unit = {
    myRunThread(()=>{
      println("感悟")
      Thread.sleep(3000)
      println("天地")
    })
  }

  def myRunThread(f:()=>Unit) ={
    new Thread{
      override def run(): Unit = {
        f()
      }
    }.start()
  }

}
