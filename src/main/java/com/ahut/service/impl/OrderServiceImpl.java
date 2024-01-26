package com.ahut.service.impl;

import com.ahut.mapper.OrderMapper;
import com.ahut.pojo.Order;
import com.ahut.service.OrderService;
import com.ahut.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    // 获取SqlSessionFactory实例
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    // 通过用户ID查询所有订单
    @Override
    public List<Order> selectOrdersByUserId(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        List<Order> orderList = mapper.selectByUserId(id);

        sqlSession.close();
        return orderList;
    }

    // 添加订单
    @Override
    public int add(Order order) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        int id = mapper.add(order);

        sqlSession.commit();
        sqlSession.close();
        return id;
    }

}