package com.ahut.mapper;

import com.ahut.pojo.Cart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CartMapper {

    // 查询指定用户的所有购物车商品
    List<Cart> selectAll(int id);

    // 向购物车表中添加一条记录
    @Insert("insert into cart (goods_id, count, user_id) values (#{goodsId},#{count},#{userId});")
    void add(Cart cart);

    // 根据购物车ID删除购物车中的一条记录
    @Delete("delete from cart where cart_id=#{cartId};")
    void delete(int cartId);

    // 根据用户ID删除该用户的所有购物车记录
    @Delete("delete from cart where user_id=#{userId};")
    void deleteByUserId(int userId);

    // 根据购物车ID更新购物车中的商品数量
    @Update("update cart set count = #{count} where cart_id=#{cartId};")
    void updateById(Cart cart);
}