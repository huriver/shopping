package com.ahut.mapper;

import com.ahut.pojo.Order;

import java.util.List;

public interface OrderMapper {


    List<Order> selectByUserId(int id);

    int add(Order order);
}
