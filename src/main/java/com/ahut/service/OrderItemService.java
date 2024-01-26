package com.ahut.service;

import com.ahut.pojo.OrderItem;

import java.util.List;

public interface OrderItemService {

    // 添加订单详情项
    void add(OrderItem orderItem);

    // 通过订单ID查询所有订单详情项
    List<OrderItem> selectAll(int orderId);
}
