package com.wtu.mall.controller;

import com.baomidou.mybatisplus.core.MybatisSqlSessionFactoryBuilder;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wtu.mall.Utils.UserMapperSqlSession;
import com.wtu.mall.mapper.UserMapper;
import com.wtu.mall.pojo.User;
import com.wtu.mall.service.UserService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @ResponseBody
    @RequestMapping("/")
    public User list() throws IOException {
        User user = new User();
        user.setName("ÕÅÈý");
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(user.getName() != null,User::getName,user.getName());
        List<User> list = userService.list(queryWrapper);
        return list.get(0);
    }
}
