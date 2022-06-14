package com.wtu.mall.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wtu.mall.pojo.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
   List<User> getById();
}
