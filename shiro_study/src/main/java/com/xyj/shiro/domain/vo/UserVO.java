package com.xyj.shiro.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by banma on 2017/6/24.
 */
@Data
public class UserVO implements Serializable{
    private String name;
    private Integer age;
    private String password;
}
