package com.dreamer.demoproject.controller;

import com.dreamer.demoproject.pojo.Article;
import com.dreamer.demoproject.pojo.PageBean;
import com.dreamer.demoproject.pojo.Result;
import com.dreamer.demoproject.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    //添加文章
    @PostMapping
    public Result add(@RequestBody @Validated Article article) {
        articleService.add(article);
        return Result.success();
    }

    //分页查询文章
    @GetMapping
    public Result<PageBean<Article>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer categoryId,//非必须
            @RequestParam(required = false) String state//非必须
    ) {
        PageBean<Article> pb = articleService.list(pageNum, pageSize, categoryId, state);
        return Result.success(pb);
    }
}
