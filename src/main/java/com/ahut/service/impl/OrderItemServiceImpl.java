package com.ahut.service.impl;

import com.ahut.mapper.OrderItemMapper;
import com.ahut.mapper.OrderMapper;
import com.ahut.pojo.OrderItem;
import com.ahut.service.OrderItemService;
import com.ahut.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class OrderItemServiceImpl implements OrderItemService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public void add(OrderItem orderItem) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        OrderItemMapper mapper = sqlSession.getMapper(OrderItemMapper.class);
        mapper.add(orderItem);

        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public List<OrderItem> selectAll(int orderId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        OrderItemMapper mapper = sqlSession.getMapper(OrderItemMapper.class);
        //根据order的id查询其orderItems
        List<OrderItem> orderItemList = mapper.selectAll(orderId);

        sqlSession.close();
        return orderItemList;
    }

}
