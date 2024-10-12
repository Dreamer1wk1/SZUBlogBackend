package com.dreamer.demoproject.service.impl;

import com.dreamer.demoproject.mapper.CategoryMapper;
import com.dreamer.demoproject.pojo.Category;
import com.dreamer.demoproject.service.CategoryService;
import com.dreamer.demoproject.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override//新增分类
    public void add(Category category) {
        //补充category属性
        category.setUpdateTime(LocalDateTime.now());
        category.setCreateTime(LocalDateTime.now());
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userID = (Integer) map.get("id");
        category.setCreateUser(userID);
        categoryMapper.add(category);
    }

    @Override//获取所有文章分类
    public List<Category> list() {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        return categoryMapper.list(userId);
    }

    @Override//获取指定id文章分类详情
    public Category findById(Integer id) {
        return categoryMapper.findById(id);
    }

    @Override //更新指定id文章分类信息
    public void update(Category category) {
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.update(category);
    }
}
