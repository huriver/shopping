package com.ahut.service.impl;

import com.ahut.mapper.CartMapper;
import com.ahut.pojo.Cart;
import com.ahut.service.CartService;
import com.ahut.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class CartServiceImpl implements CartService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public List<Cart> selectAll(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        CartMapper mapper = sqlSession.getMapper(CartMapper.class);
        List<Cart> cartList = mapper.selectAll(id);

        sqlSession.close();
        return cartList;
    }

    @Override
    public void add(Cart cart) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        CartMapper mapper = sqlSession.getMapper(CartMapper.class);
        mapper.add(cart);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void delete(int cartId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        CartMapper mapper = sqlSession.getMapper(CartMapper.class);
        mapper.delete(cartId);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteByUserId(int userId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        CartMapper mapper = sqlSession.getMapper(CartMapper.class);
        mapper.deleteByUserId(userId);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void update(Cart cart) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        CartMapper mapper = sqlSession.getMapper(CartMapper.class);
        mapper.updateById(cart);
        sqlSession.commit();
        sqlSession.close();
    }

}
