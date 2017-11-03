package com.xyj.study.sys;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by banma on 2017/11/1.
 */
@Data
@ConfigurationProperties(prefix = "weixin")
public class WeixinEntity {

    private String appid;

    private String getuuid;

    private String showQR;

}
