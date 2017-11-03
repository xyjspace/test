package com.xyj.study.streaming.util

import org.apache.log4j.{Level, Logger}
import org.apache.spark.internal.Logging


/**
  * Created by banma on 2017/9/11.
  */
object LogUtil extends Logging{

  def setStreamingLog(): Unit = {
      Logger.getRootLogger.setLevel(Level.WARN)
  }

}
