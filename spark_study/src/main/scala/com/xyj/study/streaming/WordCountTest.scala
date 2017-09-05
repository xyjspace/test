package com.xyj.study.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * Created by banma on 2017/9/4.
  */
object WordCountTest {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("word count test").setMaster("local[2]")
    //创建一个streamingContext 批处理间隔为 一秒
    val ssc = new StreamingContext(conf, Seconds(20))
    //创建一个Dstream 该Dstream的数据来自socket
    val lines = ssc.socketTextStream("localhost",9998)
    val source = lines.flatMap(_.split(" "))
    //统计
    val counts = source.map((_,1)).reduceByKey(_+_)

    counts.print()

    //以上的这些只是要执行的动作，而到现在为止批处理还没有开始，当我们使用ssc.start()的时候批处理才是真正的开始。
    ssc.start()
    //???
    ssc.awaitTermination()
    //执行 nc -lk 9998

  }
}
