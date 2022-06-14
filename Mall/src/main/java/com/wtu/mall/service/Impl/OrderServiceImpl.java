package com.wtu.mall.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wtu.mall.mapper.OrderMapper;
import com.wtu.mall.pojo.Order;
import com.wtu.mall.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
}
