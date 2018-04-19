package com.wangyuelin.app.dao;

import com.wangyuelin.app.bean.Product;
import com.wangyuelin.app.config.mybatis.baseMapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ProductDao extends BaseMapper<Product> {


    @Select("select * from product where name=#{name}")
    List<Product> setletByname(@Param("name") String name);

    @Select("select * from product where productId=#{id}")
    List<Product> selectById(@Param("id") String id);

    @Update("update product set spcialStatus=#{spcialStatus} where productId=#{id}")
    void updateSpecialStatus(@Param("id") String productId, @Param("spcialStatus") String status);

}
