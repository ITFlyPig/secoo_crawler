package com.wangyuelin.app.dao;

import com.wangyuelin.app.bean.Category;
import com.wangyuelin.app.config.mybatis.baseMapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryDao extends BaseMapper<Category>{

    @Select("select * from category where name=#{name}")
    List<Category> selectByName(@Param("name") String name);



}
