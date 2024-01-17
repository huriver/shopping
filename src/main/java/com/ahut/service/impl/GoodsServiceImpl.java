package com.ahut.service.impl;

import com.ahut.mapper.GoodsMapper;
import com.ahut.pojo.Goods;
import com.ahut.service.GoodsService;
import com.ahut.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.ArrayList;
import java.util.List;

public class GoodsServiceImpl implements GoodsService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public List<Goods> selectAll() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        GoodsMapper mapper = sqlSession.getMapper(GoodsMapper.class);
        List<Goods> goodsList = mapper.selectAll();

        sqlSession.close();
        return goodsList;
    }

    @Override
    public Goods selectById(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        GoodsMapper mapper = sqlSession.getMapper(GoodsMapper.class);
        Goods goods = mapper.selectById(id);

        sqlSession.close();
        return goods;
    }

}
