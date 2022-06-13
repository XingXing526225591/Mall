package com.wtu.mall.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private String uid;
    private String username;
    private String password;
    private String name;
    private String email;
    private Date birthday;
    private String sex;
    private Integer state;
    private String code;
}
