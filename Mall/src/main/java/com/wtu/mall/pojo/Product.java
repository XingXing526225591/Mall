package com.wtu.mall.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Product {
    private String pid;
    private String pname;
    private String marketPrice;
    private String shopPrice;
    private String pimage;
    private Date pdate;
    private Integer isHot;
    private String pdesc;
    private Integer pflag;
    private Integer cid;
}
