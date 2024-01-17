package com.ahut.mapper;


import com.ahut.pojo.Goods;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GoodsMapper {


    @Select("select * from goods;")
    List<Goods> selectAll();

    @Select("select * from goods where id=#{id};")
    Goods selectById(int id);

}
