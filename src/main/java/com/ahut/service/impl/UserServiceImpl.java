package com.ahut.service.impl;

import com.ahut.mapper.UserMapper;
import com.ahut.pojo.User;
import com.ahut.service.UserService;
import com.ahut.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;


public class UserServiceImpl implements UserService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public User login(String username, String password) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.select(username, password);

        sqlSession.close();
        return user;
    }

    public boolean register(User user) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User u = mapper.selectByUsername(user.getUsername());

        System.out.println(u == null);
        System.out.println(user.getUsername() != null);
        System.out.println(user.getPassword() != null);
        System.out.println(!"".equals(user.getUsername()));

        if (u == null && user.getUsername() != null && user.getPassword() != null
                && !"".equals(user.getUsername()) && !"".equals(user.getPassword())) {
            mapper.add(user);
            sqlSession.commit();
        }
        sqlSession.close();
        return u == null && user.getUsername() != null && user.getPassword() != null
                && !"".equals(user.getUsername()) && !"".equals(user.getPassword());
    }

    public boolean selectByUsername(String username) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User u = mapper.selectByUsername(username);
        sqlSession.close();
        return u != null;

    }

}
