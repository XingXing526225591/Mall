package com.wtu.mall.controller;

import com.wtu.mall.pojo.Order;
import com.wtu.mall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @ResponseBody
    @RequestMapping("/adminOrder_findAll.action")
    public List<Order> list(){
        List<Order> list = orderService.list();
        return list;
    }
}
