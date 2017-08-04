package com.xyj.shiro.web;

import com.xyj.shiro.domain.entity.User;
import com.xyj.shiro.util.LogUtils;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
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
//
//    private Logger log4j2 =  LogUtils.log4j2;
//    private org.apache.log4j.Logger logger = LogUtils.log4j;
    @ResponseBody
    @RequestMapping(value = "test1", method = RequestMethod.GET)
    public User test1(){


        User user = new User();
        user.setPTHaha("123312312");
        user.getPTHaha();
        return user;
    }

}
