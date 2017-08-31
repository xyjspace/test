package com.xyj.study.wordcount

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by banma on 2017/8/31.
  */
object WordCount {
  def main(args: Array[String]): Unit = {
    require(args.length >= 2)
    val conf = new SparkConf().setAppName("word_count")
    val sc = new SparkContext(conf)
    //统计
    sc.textFile(args(0)).flatMap(_.split(" "))
        .map((_, 1)).reduceByKey(_ + _, 1).sortBy(_._2, false).saveAsTextFile(args(1))
    sc.stop()
  }
}
