package com.wtu.mall.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wtu.mall.mapper.ProductMapper;
import com.wtu.mall.pojo.Product;
import com.wtu.mall.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
}
