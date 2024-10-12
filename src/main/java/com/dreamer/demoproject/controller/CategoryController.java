package com.dreamer.demoproject.controller;

import com.dreamer.demoproject.pojo.Category;
import com.dreamer.demoproject.pojo.Result;
import com.dreamer.demoproject.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    //添加文章分类
    @PostMapping//不对每个方法映射路径，同一映射整个类然后根据请求方式区分
    public Result add(@RequestBody @Validated Category category){
        categoryService.add(category);
        return Result.success();
    }
    //获取所有文章分类
    @GetMapping
    public Result<List<Category>>list(){
        List<Category> temp=categoryService.list();
        return Result.success(temp);
    }
    //获取指定id文章分类详情
    @GetMapping("/detail")
    public Result<Category> detail(Integer id){
        return Result.success(categoryService.findById(id));
    }
    @PutMapping
    public Result update(@RequestBody @Validated(Category.UpdateGroup.class) Category category){
        categoryService.update(category);
        return Result.success();
    }
}
