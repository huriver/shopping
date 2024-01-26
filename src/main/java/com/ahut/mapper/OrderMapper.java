package com.ahut.mapper;

import com.ahut.pojo.Order;

import java.util.List;

public interface OrderMapper {

    // 根据用户ID查询该用户的所有订单
    List<Order> selectByUserId(int id);

    // 向订单表中添加一条记录，返回插入的行数
    int add(Order order);
}