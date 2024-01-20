package com.ahut.mapper;


import com.ahut.pojo.Cart;
import com.ahut.pojo.Goods;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface GoodsMapper {


    @Select("select * from goods;")
    List<Goods> selectAll();

    @Select("select * from goods where id=#{id};")
    Goods selectById(int id);

    @Update("UPDATE goods SET number = #{number} WHERE id = #{goodsId}")
    void updateById(@Param("goodsId") int goodsId, @Param("number") int number);
}
