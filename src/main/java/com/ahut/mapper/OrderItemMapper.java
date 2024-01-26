package com.ahut.mapper;

import com.ahut.pojo.OrderItem;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

public interface OrderItemMapper {

    // 向订单详情表中添加一条记录
    @Insert("insert into order_item (order_id, goods_id, count, total_price) " +
            "values (#{orderId},#{goodsId},#{count},#{totalPrice});")
    void add(OrderItem orderItem);

    // 查询指定订单的所有订单详情项
    List<OrderItem> selectAll(int orderId);

}