package com.xyj.study.streaming

import com.xyj.study.streaming.util.LogUtil
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.{HashPartitioner, SparkConf}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * Created by banma on 2017/9/11.
  */
object WordCountState {
  def main(args: Array[String]): Unit = {
//    val Array(zkQuorum, group, topics, numThreads) = args
    val conf = new SparkConf().setMaster("local[1]").setAppName("wordcount_state")
    val ssc = new StreamingContext(conf, Seconds(2))
    ssc.sparkContext.setLogLevel("WARN")
//    val topicMap = topics.split(",").map((_, numThreads.toInt)).toMap
    val kafkaParams = Map[String, String]("metadata.broker.list" -> "127.0.0.1:9092")
//    val source = KafkaUtils.createStream(ssc,"localhost:2181","mykafka",oup,topicMap, StorageLevel.MEMORY_AND_DISK_SER)
    ssc.checkpoint("/Users/banma/hahaha")
//    val result = source.flatMap(_.split("\\s+")).map((_, 1)).reduceByKey(_+_)
//    updateStateByKey(func, new HashPartitioner(ssc.sparkContext.defaultParallelism), true)
//    result.print()
    ssc.start()
    ssc.awaitTermination()

  }

  val func = (iterator: Iterator[(String, Seq[Int], Option[Int])]) => {
    iterator.map { case (key, current_count, history_count) => (key, current_count.sum + history_count.getOrElse(0)) }
  }
}
