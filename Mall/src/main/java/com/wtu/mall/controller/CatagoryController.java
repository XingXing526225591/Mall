package com.wtu.mall.controller;

import com.wtu.mall.pojo.Category;
import com.wtu.mall.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CatagoryController {

    @Autowired
    private CategoryService categoryService;

    @ResponseBody
    @RequestMapping("/adminCategoryServlet")
    public List<Category> list(){
        List<Category> list = categoryService.list();
        return list;
    }
}
