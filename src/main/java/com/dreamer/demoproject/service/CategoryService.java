package com.dreamer.demoproject.service;

import com.dreamer.demoproject.pojo.Category;

import java.util.List;

public interface CategoryService {
    //新增分类
    void add(Category category);
    //获取所有文章分类
    List<Category> list();
    //获取指定id文章分类信息
    Category findById(Integer id);
    //更新指定id文章分类信息
    void update(Category category);
}
