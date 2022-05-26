package com.wtu.travel.Dao;

import com.wtu.travel.Utils.UserMapperSqlSession;
import com.wtu.travel.bean.User;
import com.wtu.travel.bean.UserExample;
import com.wtu.travel.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public class UserDao {

    public User getUserByuserName(SqlSession userSqlSession,String userName) throws IOException {
        List<User> users = null;

            UserMapper mapper = userSqlSession.getMapper(UserMapper.class);
            UserExample example = new UserExample();
            example.createCriteria().andUsernameEqualTo(userName);
            users = mapper.selectByExample(example);

               if (users.size()!=0) {
                   return users.get(0);
               }else {
                   return null;
               }
    }

    public User selUserById(SqlSession userSqlSession,Integer id){
        UserMapper mapper = userSqlSession.getMapper(UserMapper.class);
        UserExample example = new UserExample();
        example.createCriteria().andIdEqualTo(id);
        List<User> users = mapper.selectByExample(example);
        if (users.size()!=0) {
            return users.get(0);
        }else {
            return null;
        }
    }

    public void addOneUser(SqlSession userSqlSession,User user){
        UserMapper mapper = userSqlSession.getMapper(UserMapper.class);
        mapper.insert(user);
    }
}
