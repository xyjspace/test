package com.xyj.shiro.web;

import com.xyj.shiro.domain.entity.User;
import com.xyj.shiro.util.LogUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by banma on 2017/6/21.
 */
@Controller
public class TestWeb {

    private Logger logger =  LogUtils.logger;

    @ResponseBody
    @RequestMapping(value = "test1", method = RequestMethod.GET)
    public User test1(){
        User user = new User();
        user.setName("xunyajie");


        logger.trace("trace level");
        logger.debug("debug level");
        logger.info("info level");
        logger.warn("warn level");
        logger.error("error level");
        logger.fatal("fatal level");

        return user;
    }

}
