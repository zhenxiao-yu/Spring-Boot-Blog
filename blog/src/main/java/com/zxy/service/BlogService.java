package com.zxy.service;

import com.zxy.po.Blog;
import com.zxy.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;


public interface BlogService {

    //retrieve blog post by id
    Blog getBlog(Long id);

    //convert content from repository (md) to displayed post (html)
    Blog getAndConvert(Long id);

    //retrieve a group of blogs
    Page<Blog> listBlog(Pageable pageable,BlogQuery blog);

    //list of blogs in pages
    Page<Blog> listBlog(Pageable pageable);

    //
    Page<Blog> listBlog(Long tagId,Pageable pageable);

    //list of blogs for search result
    Page<Blog> listBlog(String query,Pageable pageable);

    //list of recommended blogs
    List<Blog> listRecommendBlogTop(Integer size);

    //list of archive blogs
    Map<String,List<Blog>> archiveBlog();

    //number of blogs
    Long countBlog();

    //add new blog post
    Blog saveBlog(Blog blog);

    //update blog post
    Blog updateBlog(Long id,Blog blog);

    //delete blog post
    void deleteBlog(Long id);
}
