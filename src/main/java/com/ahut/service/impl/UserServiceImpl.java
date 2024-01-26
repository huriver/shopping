package com.ahut.service.impl;

import com.ahut.mapper.UserMapper;
import com.ahut.pojo.User;
import com.ahut.service.UserService;
import com.ahut.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class UserServiceImpl implements UserService {
    // 创建SqlSessionFactory对象
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    // 用户登录方法
    public User login(String username, String password) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.select(username, password);

        sqlSession.close();
        return user;
    }

    // 用户注册方法
    public boolean register(User user) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User u = mapper.selectByUsername(user.getUsername());

        // 判断用户是否可以注册
        if (u == null && user.getUsername() != null && user.getPassword() != null
                && !"".equals(user.getUsername()) && !"".equals(user.getPassword())) {
            // 添加用户
            mapper.add(user);
            // 提交事务
            sqlSession.commit();
        }
        sqlSession.close();
        // 返回注册结果
        return u == null && user.getUsername() != null && user.getPassword() != null
                && !"".equals(user.getUsername()) && !"".equals(user.getPassword());
    }

    // 通过用户名查询用户方法
    public boolean selectByUsername(String username) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User u = mapper.selectByUsername(username);
        sqlSession.close();
        // 返回查询结果
        return u != null;
    }

}