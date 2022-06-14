package com.wtu.mall.controller;

import com.wtu.mall.pojo.Product;
import com.wtu.mall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @ResponseBody
    @RequestMapping("adminProduct_findAll.action")
    public List<Product> list(){

        List<Product> list = productService.list();
        return list;
    }
}
