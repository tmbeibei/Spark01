package com.atguigu.bigdata.stream

import java.io.{BufferedReader, InputStreamReader}
import java.net.Socket

import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.receiver.Receiver

/**
 * @author tianmin
 * @date 2020/2/28 0028
 * @notes 自定义接收器，读取Socket端口数据
 */
class MyReceiver(host:String,port:Int) extends Receiver[String](StorageLevel.MEMORY_AND_DISK_2){

  var socket:Socket = _

  def receiver(): Unit ={
    // 初始化socket
    socket = new Socket(host,port)
    // 创建BufferedReader 读取一行
    val reader = new BufferedReader(new InputStreamReader(socket.getInputStream,"utf-8"))
    var line:String = null
    while((line = reader.readLine()) != null){
      if("END".equals(line)){
        return
      }else{
        // 存储数据集
        this.store(line)
      }
    }

    // 关闭资源
    reader.close()
  }

  override def onStart(): Unit = {
    new Thread(new Runnable {
      override def run(): Unit = {
        receiver()
      }
    }).start()
  }

  override def onStop(): Unit = {
    if(socket != null){
      socket.close()
      socket = null
    }
  }
}
