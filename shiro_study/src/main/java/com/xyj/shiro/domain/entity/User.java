package com.xyj.shiro.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by banma on 2017/6/21.
 */
@Data
public class User implements Serializable{
    private String name;
    private Integer age;
    private String password;
    private String salt;
    @JsonProperty("pThhaha")
    private String PTHaha;
    public String getCredentialsSalt() {
        return name + salt;
    }


    public Integer getAge() {
        return age;
    }
}

