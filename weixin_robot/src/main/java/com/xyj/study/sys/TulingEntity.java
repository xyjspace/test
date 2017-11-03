package com.xyj.study.sys;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by banma on 2017/10/31.
 */
@Data
@ConfigurationProperties(prefix = "tuling")
public class TulingEntity {

    private String apikey;

    private String postLocation;

}
