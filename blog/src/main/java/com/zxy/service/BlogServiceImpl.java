package com.zxy.service;

import com.zxy.NotFoundException;
import com.zxy.dao.BlogRepository;
import com.zxy.po.Blog;
import com.zxy.po.Type;
import com.zxy.util.MyBeanUtils;
import com.zxy.vo.BlogQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository; //declare new blog repository

    @Override
    public Blog getBlog(Long id) {
        return blogRepository.findOne(id);
    }


    @Override
    public Page<Blog> listBlog(Pageable pageable, BlogQuery blog) {
        //look for all the current blog posts in the repo
        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                //root = post
                //CriteriaQuery = searching container
                //CriteriaBuilder = rules and predicating search
                List<Predicate> predicates = new ArrayList<>();
                //title is not empty
                if (!"".equals(blog.getTitle()) && blog.getTitle() != null) {
                    predicates.add(cb.like(root.<String>get("title"), "%" + blog.getTitle() + "%"));
                }
                //search by category using it's id
                if (blog.getTypeId() != null) {
                    predicates.add(cb.equal(root.<Type>get("type").get("id"), blog.getTypeId()));
                }
                //search by isRecommend check mark
                if (blog.isRecommend()) {
                    predicates.add(cb.equal(root.<Boolean>get("recommend"), blog.isRecommend()));
                }
                //cast to array
                cq.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        }, pageable);
    }

    //list of blogs in pages
    @Override
    public Page<Blog> listBlog(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    //list of blogs for search result
    @Override
    public Page<Blog> listBlog(String query, Pageable pageable) {
        return blogRepository.findByQuery(query,pageable);
    }

    //list of recommended blogs
    @Override
    public List<Blog> listRecommendBlogTop(Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC,"updateTime");
        Pageable pageable = new PageRequest(0, size, sort);
        return blogRepository.findTop(pageable);
    }

    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        //save current blog
        if (blog.getId() == null) {
            //save time stamp
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            //init view number
            blog.setViews(0);
        } else {
            blog.setUpdateTime(new Date());
        }
        return blogRepository.save(blog);
    }

    @Transactional
    @Override
    public Blog updateBlog(Long id, Blog blog) {
        //update post content
        Blog b = blogRepository.findOne(id);
        if (b == null) {
            throw new NotFoundException("Post does not exist");
        }
        //filter out null properties with BeanUtils
        BeanUtils.copyProperties(blog, b, MyBeanUtils.getNullPropertyNames(blog));
        b.setUpdateTime(new Date());
        return blogRepository.save(b);
    }

    @Transactional
    @Override
    public void deleteBlog(Long id) {
        //delete blog post
        blogRepository.delete(id);
    }
}
