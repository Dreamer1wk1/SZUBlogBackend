package com.dreamer.demoproject.mapper;

import com.dreamer.demoproject.pojo.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CategoryMapper {
    //新增
    @Insert("insert into category(category_name,category_alias,create_user,create_time,update_time)"
            + "values (#{categoryName},#{categoryAlias},#{createUser},#{createTime},#{updateTime})")
    void add(Category category);

    //获取所有文章分类
    @Select("select * from category where create_user=#{userId}")
    List<Category> list(Integer userId);

    //获取指定id文章分类详情
    @Select("select * from category where id=#{id}")
    Category findById(Integer id);

    //更新指定id文章分类详情
    @Update("update category set category_name=#{categoryName},category_alias=#{categoryAlias},update_time=#{updateTime} where id=#{id}")
    void update(Category category);
}
