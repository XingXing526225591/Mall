package com.wtu.travel.servlet;

import com.wtu.travel.Dao.UserDao;
import com.wtu.travel.Utils.UserMapperSqlSession;
import com.wtu.travel.bean.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserServlet {

    @Autowired
    private UserDao userDao;

    public User getUserByUserName(String userName) {
        SqlSession userSqlSession = null;
        User userByuserName = null;
        try {
            userSqlSession = UserMapperSqlSession.getUserSqlSession();
            userByuserName = userDao.getUserByuserName(userSqlSession, userName);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            userSqlSession.close();
        }

        return userByuserName;
    }

    public void addOneUser(User user){
        SqlSession userSqlSession = null;
        try {
            userSqlSession = UserMapperSqlSession.getUserSqlSession();
            userDao.addOneUser(userSqlSession,user);
            userSqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            userSqlSession.close();
        }
    }

}
