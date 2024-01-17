package com.ahut.service;

import com.ahut.pojo.Goods;

import java.util.List;

public interface GoodsService {
    List<Goods> selectAll();

    Goods selectById(int id);

}
