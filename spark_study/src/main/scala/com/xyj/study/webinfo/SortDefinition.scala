package com.xyj.study.webinfo

import java.io.Serializable

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by banma on 2017/9/4.
  */
object SortDefinition {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("sortDemo").setMaster("local")
    val sc = new SparkContext(conf)
    val source = Array(new Student("zhangsan", 16, 174.1),new Student("Lisi",17,164.2), new Student("zhanghan",21,180.1))
    val sourceRDD = sc.parallelize(source,1)
    sourceRDD.sortBy(x => x).saveAsTextFile("spark_study/result/06")
    sc.stop()

  }
}


class Student(val name:String , val age:Int, val height: Double) extends Serializable with Ordered[Student]{


  override def toString = s"Student($name $age $height)"

  override def compare(that: Student): Int = {
    if ((this.height - that.height) > 0) 1 else -1
  }
}


