package com.atguigu.bigdata.stream

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @author tianmin
 * @date 2020/2/28 0028
 * @notes
 */
object StreamWordCount {
  def main(args: Array[String]): Unit = {
    // 配置文件
    val config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("StreamWordCount")
    // 初始化StreamingContext
    val streamingContext = new  StreamingContext(config,Seconds(3))

    // 通过监控端口创建Dstream，读进来的数据是一行行的
    val lineDstream: ReceiverInputDStream[String] = streamingContext.socketTextStream("hadoop101",4444)

    val wordDstream: DStream[(String, Int)] = lineDstream.flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_)

    //打印
    wordDstream.print()

    //启动
    streamingContext.start()
    streamingContext.awaitTermination()
  }

}
