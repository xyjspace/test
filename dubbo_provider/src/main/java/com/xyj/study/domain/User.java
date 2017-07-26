package com.xyj.study.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by banma on 2017/7/25.
 */
@Data
public class User implements Serializable{
    String name;
    Integer age;
}
