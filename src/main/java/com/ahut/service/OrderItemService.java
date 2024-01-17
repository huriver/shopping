package com.ahut.service;

import com.ahut.pojo.OrderItem;

import java.util.List;

public interface OrderItemService {

    void add(OrderItem orderItem);

    List<OrderItem> selectAll(int orderId);
}
