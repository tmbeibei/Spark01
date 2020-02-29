package com.atguigu.chapter13

/**
 * @author tianmin
 * @date 2020/2/22 0022
 * @notes
 */
object test11 {
  def main(args: Array[String]): Unit = {
    val f = makesuffix(".jpg")
    println("类型:" +  f)
    println(f("dog.jpg"))
    println(f("cat"))
  }

  def makesuffix(suffix:String) ={
    (filename:String)=>{
      if(filename.endsWith(suffix)){
        filename
      }else{
        filename + suffix
      }
    }
  }

}
