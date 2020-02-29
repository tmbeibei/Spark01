package com.atguigu.bigdata.stream

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @author tianmin
 * @date 2020/2/28 0028
 * @notes
 */
object StreamWordCountDefine {
  def main(args: Array[String]): Unit = {
    // 配置文件
    val config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("StreamWordCountDefine")
    // 初始化StreamingContext
    val streamingContext = new  StreamingContext(config,Seconds(5))

    //通过receiverStream调用
    val myReceiverDstream: ReceiverInputDStream[String] = streamingContext.receiverStream(new MyReceiver("hadoop101",44444))

    val wordDstream: DStream[(String, Int)] = myReceiverDstream.flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_)

    //打印
    wordDstream.print()

    //启动
    streamingContext.start()
    streamingContext.awaitTermination()
  }

}
