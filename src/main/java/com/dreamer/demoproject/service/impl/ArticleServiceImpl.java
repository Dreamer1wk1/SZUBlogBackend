package com.dreamer.demoproject.service.impl;

import com.dreamer.demoproject.mapper.ArticleMapper;
import com.dreamer.demoproject.pojo.Article;
import com.dreamer.demoproject.pojo.PageBean;
import com.dreamer.demoproject.service.ArticleService;
import com.dreamer.demoproject.util.ThreadLocalUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service

public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    //添加文章
    @Override
    public void add(Article article) {
        //补充属性值
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());
        Map<String,Object> map= ThreadLocalUtil.get();
        Integer id= (Integer) map.get("id");
        article.setCreateUser(id);
        articleMapper.add(article);
    }
    //分页查询文章
    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        //创建BeanPage对象
        PageBean<Article>pb=new PageBean<>();
        //开启分页查询(pom中引入PageHelper坐标)
        PageHelper.startPage(pageNum,pageSize);
        //调用mapper查询
        Map<String,Object> map= ThreadLocalUtil.get();
        Integer userId= (Integer) map.get("id");
        List<Article>la=articleMapper.list(userId,categoryId,state);
        //将查询结果la强制转换为Page<Article>类型，通过p对象获取到Page对象提供的各种分页信息
        Page<Article>p=(Page<Article>) la;
        //将数据填充到PageBean对象
        //将Page对象中的总记录数（即总条目数）设置到PageBean<Article>对象中
        pb.setTotal(p.getTotal());
        //将Page对象中当前页的数据（即查询结果）设置到PageBean<Article>对象中的items属性中
        pb.setItems(p.getResult());
        return pb;
    }
}
