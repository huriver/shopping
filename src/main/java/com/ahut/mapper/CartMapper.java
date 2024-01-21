package com.ahut.mapper;

import com.ahut.pojo.Cart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CartMapper {

    List<Cart> selectAll(int id);

    @Insert("insert into cart (goods_id, count, user_id) values (#{goodsId},#{count},#{userId});")
    void add(Cart cart);

    @Delete("delete from cart where cart_id=#{cartId};")
    void delete(int cartId);

    @Delete("delete from cart where user_id=#{userId};")
    void deleteByUserId(int userId);

    @Update("update cart set count = #{count} where cart_id=#{cartId};")
    void updateById(Cart cart);
}
