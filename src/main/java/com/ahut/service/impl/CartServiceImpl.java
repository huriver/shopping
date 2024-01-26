package com.ahut.service.impl;

import com.ahut.mapper.CartMapper;
import com.ahut.pojo.Cart;
import com.ahut.service.CartService;
import com.ahut.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class CartServiceImpl implements CartService {
    // 获取SqlSessionFactory实例
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    // 通过用户ID查询所有购物车项
    @Override
    public List<Cart> selectAll(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        CartMapper mapper = sqlSession.getMapper(CartMapper.class);
        List<Cart> cartList = mapper.selectAll(id);

        sqlSession.close();
        return cartList;
    }

    // 添加购物车项
    @Override
    public void add(Cart cart) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        CartMapper mapper = sqlSession.getMapper(CartMapper.class);
        mapper.add(cart);
        sqlSession.commit();
        sqlSession.close();
    }

    // 通过购物车ID删除购物车项
    @Override
    public void delete(int cartId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        CartMapper mapper = sqlSession.getMapper(CartMapper.class);
        mapper.delete(cartId);
        sqlSession.commit();
        sqlSession.close();
    }

    // 通过用户ID删除所有购物车项
    @Override
    public void deleteByUserId(int userId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        CartMapper mapper = sqlSession.getMapper(CartMapper.class);
        mapper.deleteByUserId(userId);
        sqlSession.commit();
        sqlSession.close();
    }

    // 更新购物车项的商品数量
    @Override
    public void update(Cart cart) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        CartMapper mapper = sqlSession.getMapper(CartMapper.class);
        mapper.updateById(cart);
        sqlSession.commit();
        sqlSession.close();
    }

}