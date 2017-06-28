package com.xyj.shiro.util;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by banma on 2017/6/23.
 */
public class LogUtils {
    public static final Logger log4j2 = LogManager.getLogger(LogUtils.class.getName() + ".log4j2");
    public static final org.apache.log4j.Logger log4j = org.apache.log4j.Logger.getLogger(LogUtils.class.getName() + "log4j");
}
