package com.ahut.service;

import com.ahut.pojo.Cart;
import java.util.List;

public interface CartService {

    // 通过用户ID查询所有购物车项
    List<Cart> selectAll(int id);

    // 添加购物车项
    void add(Cart cart);

    // 通过购物车ID删除购物车项
    void delete(int cartId);

    // 通过购物车ID删除购物车项
    void deleteByUserId(int userId);

    // 更新购物车项的商品数量
    void update(Cart cart);
}