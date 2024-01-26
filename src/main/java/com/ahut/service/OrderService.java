package com.ahut.service;

import com.ahut.pojo.Order;

import java.util.List;

public interface OrderService {

    // 通过用户ID查询所有订单
    List<Order> selectOrdersByUserId(int id);

    // 添加订单
    int add(Order order);
}
