package com.ahut.service;

import com.ahut.pojo.Order;

import java.util.List;

public interface OrderService {
    List<Order> selectOrdersByUserId(int id);

    int add(Order order);
}
