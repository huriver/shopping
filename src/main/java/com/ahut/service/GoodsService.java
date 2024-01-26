package com.ahut.service;

import com.ahut.pojo.Cart;
import com.ahut.pojo.Goods;

import java.util.List;

public interface GoodsService {

    // 查询所有商品
    List<Goods> selectAll();

    // 通过商品ID查询商品
    Goods selectById(int id);

    // 更新商品库存
    void updateCount(Cart cart);
}
