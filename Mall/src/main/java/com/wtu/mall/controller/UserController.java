package com.wtu.mall.controller;

import com.wtu.mall.Utils.UserMapperSqlSession;
import com.wtu.mall.mapper.UserMapper;
import com.wtu.mall.pojo.User;
import com.wtu.mall.service.UserService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;


@Controller
public class UserController {

    @ResponseBody
    @RequestMapping("/")
    public User list() throws IOException {

        SqlSession userSqlSession = UserMapperSqlSession.getUserSqlSession();
        UserMapper mapper = userSqlSession.getMapper(UserMapper.class);
        List<User> list = mapper.getById();
        return list.get(0);
    }
}
