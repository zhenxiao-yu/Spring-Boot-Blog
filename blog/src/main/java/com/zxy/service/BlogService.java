package com.zxy.service;

import com.zxy.po.Blog;
import com.zxy.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BlogService {

    //retrieve blog post by id
    Blog getBlog(Long id);

    //retrieve a group of blogs
    Page<Blog> listBlog(Pageable pageable,BlogQuery blog);

    //add new blog post
    Blog saveBlog(Blog blog);

    //update blog post
    Blog updateBlog(Long id,Blog blog);

    //delete blog post
    void deleteBlog(Long id);
}
