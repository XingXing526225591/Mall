package com.wtu.mall.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wtu.mall.mapper.CategoryMapper;
import com.wtu.mall.pojo.Category;
import com.wtu.mall.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class GateoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
}
