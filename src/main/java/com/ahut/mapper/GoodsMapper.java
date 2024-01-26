package com.ahut.mapper;

import com.ahut.pojo.Goods;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface GoodsMapper {

    // 查询所有商品信息
    @Select("select * from goods;")
    List<Goods> selectAll();

    // 根据商品ID查询商品信息
    @Select("select * from goods where id=#{id};")
    Goods selectById(int id);

    // 根据商品ID更新商品的库存
    @Update("UPDATE goods SET number = #{number} WHERE id = #{goodsId}")
    void updateById(@Param("goodsId") int goodsId, @Param("number") int number);
}