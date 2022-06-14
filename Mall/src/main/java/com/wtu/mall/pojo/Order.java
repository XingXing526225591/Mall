package com.wtu.mall.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("orders")
public class Order {
    private String oid;
    private Date ordertime;
    private Double total;
    private Integer state;
    private String address;
    private String name;
    private String telephone;
    private String uid;
}
