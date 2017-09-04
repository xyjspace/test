package com.xyj.study.webinfo

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by banma on 2017/9/1.
  *
  * 计算网站的访问量的top5
  **/
object TotalTop5 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("totalTop10").setMaster("local")
    val sc = new SparkContext(conf)
    val source = sc.textFile("spark_study/log/itcast.log")

    //统计访问次数
    val count = source.map(line =>{
      val f = line.split("\\s+")
      (f(1),1)
    })

    val top5 = count.reduceByKey(_+_).sortBy(_._2,false).take(5)

    println(top5.toBuffer)
  }
}
