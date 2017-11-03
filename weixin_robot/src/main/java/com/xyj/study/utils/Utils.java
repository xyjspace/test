package com.xyj.study.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by banma on 2017/11/2.
 */
public class Utils {

    /**
     * 正则匹配
     *
     * @param aim
     * @param target
     * @return
     */
    public static String match(String aim, String target) {
        Pattern pattern = Pattern.compile(aim);
        Matcher matcher = pattern.matcher(target);
        if(matcher.find()){
            return matcher.group(1);
        }
        return null;
    }

}
