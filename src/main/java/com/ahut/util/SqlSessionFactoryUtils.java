package com.ahut.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionFactoryUtils {

    // 声明一个私有的静态SqlSessionFactory对象
    private static SqlSessionFactory sqlSessionFactory;

    // 静态代码块，在类被加载时自动执行，且只执行一次
    // 用于初始化SqlSessionFactory对象
    static {
        try {
            // 指定MyBatis配置文件的路径
            String resource = "mybatis-config.xml";
            // 使用Resources工具类获取配置文件的输入流
            InputStream inputStream = Resources.getResourceAsStream(resource);
            // 使用SqlSessionFactoryBuilder的build方法根据配置信息创建SqlSessionFactory对象
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 定义一个公共的静态方法，用于外部获取SqlSessionFactory对象
    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

}