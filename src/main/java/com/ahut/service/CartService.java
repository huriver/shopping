package com.ahut.service;

import com.ahut.pojo.Cart;

import java.util.List;

public interface CartService {
    List<Cart> selectAll(int id);

    void add(Cart cart);

    void delete(int cartId);

    void deleteByUserId(int userId);

    void update(Cart cart);
}
