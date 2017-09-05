package com.xyj.study.webinfo

import java.net.URL

import org.apache.spark._

import scala.collection.mutable

/**
  * Created by banma on 2017/9/3.
  * 根据域名分区
  */
object PartitonInfo {
  def main(args: Array[String]): Unit = {

    val sc = new SparkContext(new SparkConf().setAppName("partition Info").setMaster("local[2]"))

    val source = sc.textFile("spark_study/log/itcast.log").map(it => {
      val f = it.split("\\s+")
      (f(1), 1)
    })

    //    val count = source.reduceByKey(_+_).repartition(3).saveAsTextFile("spark_study/result/02")
    //    val count = source.reduceByKey(_+_).partitionBy(new HashPartitioner(3)).saveAsTextFile("spark_study/result/03")


    val rdd2 = source.reduceByKey(_ + _)
    val rdd3 = rdd2.map(it => {
      val f = new URL(it._1).getHost
      (f, (it._1, it._2))
    })

    val ints = rdd3.map(_._1).distinct().collect()

    rdd3.partitionBy(new HostPartitioner(ints)).mapPartitions(it => {
      it.toList.sortBy(_._2._2).reverse.take(3).iterator
    }).saveAsTextFile("spark_study/result/08")

    sc.stop()


  }


}

class HostPartitioner(ints: Array[String]) extends Partitioner {


  val partitionMap = new mutable.HashMap[String, Int]()
  var count = 0

  for (it <- ints) {
    partitionMap.put(ints(count), count)
    count += 1
  }

  override def numPartitions: Int = ints.length

  override def getPartition(key: Any): Int = {
    partitionMap.getOrElse(key.toString, 0)
  }
}
