package com.xyj.study.ipdemo

import org.apache.spark.rdd.JdbcRDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by banma on 2017/9/5.
  * ....jdbcRDD好像只支持 long 的范围。。。、
  * 
  */
//object GetFromMysql {
//  def main(args: Array[String]): Unit = {
//    val conf = new SparkConf().setAppName("get_info_from_msql").setMaster("local[3]")
//    val sc = new SparkContext(conf)
//
//    val jdbcRDD = new JdbcRDD(
//      sc,
//      StoreMysql.getConnection,
//      "select * from location_info where 1 = ?",
//      1,,2,
//}
