package com.ahut.mapper;

import com.ahut.pojo.OrderItem;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

public interface OrderItemMapper {

    @Insert("insert into order_item (order_id, goods_id, count, total_price) " +
            "values (#{orderId},#{goodsId},#{count},#{totalPrice});")
    void add(OrderItem orderItem);


    List<OrderItem> selectAll(int orderId);

}
