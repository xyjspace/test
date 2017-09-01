package yinshi

import java.io.File

import scala.io.Source

/**
  * Created by banma on 2017/8/31.
  */
object implicitTest{

  def main(args: Array[String]): Unit = {
    val file = new File("/Users/banma/docker/haha.txt")
    val a = Source.fromFile(file)
    println(a.toString())
  }

}


