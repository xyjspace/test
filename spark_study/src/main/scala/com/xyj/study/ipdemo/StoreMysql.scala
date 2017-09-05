package com.xyj.study.ipdemo

import java.sql.{Connection, Date, DriverManager, PreparedStatement}

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by banma on 2017/9/5.
  */
object StoreMysql {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("store_ip").setMaster("local[3]")
    val sc = new SparkContext(conf)

    val ipRules = sc.textFile("spark_study/log/ip.txt").map(lines => {
      val f = lines.split("\\|")
      (f(2), f(3), f(6))
    })
    val a = ipRules.collect()
    //将ip规则从driver广播到存有job的worker中
    val ipRulesBroadcast = sc.broadcast(a)

    //获取ips
    val ips = sc.textFile("spark_study/log/access_log").map(lines => {
      val f = lines.split("\\|")
      f(1)
    })

    //包装数据
    val result = ips.map(ip => {
      val ipLong = IpConvert.ip2Long(ip)
      val f = this.binarySearch(ipRulesBroadcast.value, ipLong)
      val result = ipRulesBroadcast.value(f)
      (result._3, 1)
    }).reduceByKey(_ + _)

    result.foreachPartition(writeToMysql(_))

    //    result.repartition(1).saveAsTextFile("spark_study/result/07")
    sc.stop()
  }

  def binarySearch(lines: Array[(String, String, String)], ip: Long): Int = {
    var low = 0
    var high = lines.length - 1
    while (low <= high) {
      val middle = (low + high) / 2
      if ((ip >= lines(middle)._1.toLong) && (ip <= lines(middle)._2.toLong))
        return middle
      if (ip < lines(middle)._1.toLong)
        high = middle - 1
      else {
        low = middle + 1
      }
    }
    -1
  }

  def writeToMysql(it: Iterator[(String, Int)]) = {
    var conn: Connection = null;
    var ps: PreparedStatement = null
    val sql = "insert into location_info(province, count, time) values (?,?,?)"
    try {
      conn = getConnection
      it.foreach(each => {
        ps = conn.prepareStatement(sql)
        ps.setString(1, each._1)
        ps.setInt(2, each._2)
        ps.setDate(3, new Date(System.currentTimeMillis()))
        ps.executeUpdate()
      })
    } catch {
      case e: Exception => println(e)
    } finally {
      if (ps != null)
        ps.close()
      if (conn != null)
        conn.close()
    }
  }

  def getConnection = {
    val conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spark_test?useUnicode=true&characterEncoding=utf8", "root", "root")
    conn
  }
}
