package com.wtu.travel.Utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class UserMapperSqlSession {

    public static SqlSession getUserSqlSession() throws IOException {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis--config.xml");
        SqlSessionFactory build = sqlSessionFactoryBuilder.build(resourceAsStream);
        return build.openSession();
    }
}
