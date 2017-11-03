package com.xyj.study.web;

import com.xyj.study.service.impl.WeixinLoginServiceImpl;
import com.xyj.study.sys.TulingEntity;
import com.xyj.study.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by banma on 2017/10/31.
 */
@Controller
public class TestController {
    @Autowired
    TulingEntity tulingEntity;
    @Autowired
    HttpUtils httpUtils;
    @Autowired
    WeixinLoginServiceImpl weixinLoginService;

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return tulingEntity.getApikey();
    }

    @RequestMapping("/askTest")
    @ResponseBody
    public String askTest(String message) {
        if (StringUtils.isEmpty(message)) {
            return "消息不能为空";
        }
        Map<String,String> postMap = new HashMap();
        postMap.put("key", tulingEntity.getApikey());
        postMap.put("info",message);
        return httpUtils.postContent(tulingEntity.getPostLocation(), postMap);
    }

    @RequestMapping("/getuuidTest")
    @ResponseBody
    public String getUUIDTest(){
        System.setProperty ("jsse.enableSNIExtension", "false");
        return weixinLoginService.getUUID();
    }

    @RequestMapping("/showQR")
    public String showQR(){
        System.setProperty ("jsse.enableSNIExtension", "false");
        return weixinLoginService.showQR();
    }
}
