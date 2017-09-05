package com.xyj.study.ipdemo

import java.io.{BufferedReader, FileInputStream, InputStreamReader}

import scala.collection.mutable.ArrayBuffer

/**
  * Created by banma on 2017/9/5.
  */
object IpConvert {

  def ip2Long(ipsource: String): Long = {
    val ip = ipsource.split("\\.")
    var result = 0L
    for (i <- 0 until ip.length) {
      result = ip(i).toLong | result << 8L
    }
    result
  }

  def readIp(address: String) = {
    var is = new BufferedReader(new InputStreamReader(new FileInputStream(address)))
    var s: String = null;
    var flag = true;
    var lines = new ArrayBuffer[String]()
    while (flag) {
      s = is.readLine()
      if (s != null) {
        lines += s
      } else {
        flag = false
      }
    }
    lines
  }

  def binarySearch(source: ArrayBuffer[String], ip: Long): Int = {
    var low = 0
    var high = source.length - 1
    while (low <= high) {
      val middle = (low + high) / 2
      if (ip >= source(middle).split("\\|")(2).toLong && ip <= source(middle).split("\\|")(3).toLong) {
        return middle
      } else if (ip < source(middle).split("\\|")(2).toLong) {
        high = middle - 1
      } else {
        low = middle + 1
      }
    }
      return -1
  }

  def main(args: Array[String]): Unit = {

    val ip = ip2Long("59.155.64.8")
    val lines = readIp("spark_study/log/ip.txt")
    val middle = binarySearch(lines, ip)
    println(lines(middle))

  }
}
