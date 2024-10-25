package com.szublog.service;

import com.szublog.pojo.Article;
import com.szublog.pojo.PageBean;

public interface ArticleService {
    //添加文章
    void add(Article article);
    //分页查询文章
    PageBean<Article>  list(Integer pageNum, Integer pageSize, Integer categoryId, String state);
}
