package com.ahut.service.impl;

import com.ahut.mapper.GoodsMapper;
import com.ahut.pojo.Cart;
import com.ahut.pojo.Goods;
import com.ahut.service.GoodsService;
import com.ahut.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class GoodsServiceImpl implements GoodsService {
    // 获取SqlSessionFactory实例
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    // 查询所有商品
    @Override
    public List<Goods> selectAll() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        GoodsMapper mapper = sqlSession.getMapper(GoodsMapper.class);
        List<Goods> goodsList = mapper.selectAll();

        sqlSession.close();
        return goodsList;
    }

    // 通过商品ID查询商品
    @Override
    public Goods selectById(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        GoodsMapper mapper = sqlSession.getMapper(GoodsMapper.class);
        Goods goods = mapper.selectById(id);

        sqlSession.close();
        return goods;
    }

    // 更新商品库存
    @Override
    public void updateCount(Cart cart) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        GoodsMapper mapper = sqlSession.getMapper(GoodsMapper.class);
        int number = mapper.selectById(cart.getGoodsId()).getNumber() - cart.getCount();

        mapper.updateById(cart.getGoodsId(), number);
        sqlSession.commit();
        sqlSession.close();
    }

}