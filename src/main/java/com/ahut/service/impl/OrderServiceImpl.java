package com.ahut.service.impl;

import com.ahut.mapper.CartMapper;
import com.ahut.mapper.GoodsMapper;
import com.ahut.mapper.OrderMapper;
import com.ahut.pojo.Cart;
import com.ahut.pojo.Goods;
import com.ahut.pojo.Order;
import com.ahut.service.OrderService;
import com.ahut.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public List<Order> selectOrdersByUserId(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        List<Order> orderList = mapper.selectByUserId(id);

        sqlSession.close();
        return orderList;
    }

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
