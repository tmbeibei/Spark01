package com.atguigu.chapter07.work1

/**
 * @author tianmin
 * @date 2020/2/20 0020
 * @notes
 */
class Computer {
  var cpu:String = _
  var memory:Int = _
  var disk:Int = _

  /**
   * 返回详细信息
   * @return
   */
  def getDetails(): String ={
    "cpu:" + cpu + "\tmemory:" + memory + "\tdisk:" + disk
  }
}
