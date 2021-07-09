package com.zxy.service;
//dependencies

import com.zxy.NotFoundException;
import com.zxy.repo.BlogRepository;
import com.zxy.po.Blog;
import com.zxy.po.Type;
import com.zxy.util.MarkdownUtils;
import com.zxy.util.MyBeanUtils;
import com.zxy.query.BlogQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.*;


@Service
public class BlogServiceImpl implements BlogService {

    //declare new blog repository
    @Autowired
    private BlogRepository blogRepository;

    //retrieve blog post by id
    @Override
    public Blog getBlog(Long id) {
        return blogRepository.findOne(id);
    }

    //convert content from repository (md) to displayed post (html)
    @Transactional
    @Override
    public Blog getAndConvert(Long id) {
        Blog blog = blogRepository.findOne(id);
        //error handling when blog is null
        if (blog == null) {
            throw new NotFoundException("Post does not exist!");
        }
        //new instance of blog
        Blog b = new Blog();
        //copy properties to new instance of blog
        BeanUtils.copyProperties(blog, b);
        String content = b.getContent();
        b.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        //update views
        blogRepository.updateViews(id);
        return b;
    }

    //list of blogs from blogQuery
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
                //cast to an array
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

    //list of blogs associated with a specific tag
    @Override
    public Page<Blog> listBlog(Long tagId, Pageable pageable) {
        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                Join join = root.join("tags");
                return cb.equal(join.get("id"), tagId);
            }
        }, pageable);
    }

    //list of blogs for search result
    @Override
    public Page<Blog> listBlog(String query, Pageable pageable) {
        return blogRepository.findByQuery(query, pageable);
    }

    //list of recommended blogs
    @Override
    public List<Blog> listRecommendBlogTop(Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        Pageable pageable = new PageRequest(0, size, sort);
        return blogRepository.findTop(pageable);
    }

    //list of archived blogs
    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years = blogRepository.findGroupYear();
        Map<String, List<Blog>> map = new HashMap<>();
        for (String year : years) {
            map.put(year, blogRepository.findByYear(year));
        }
        return map;
    }

    //number of blogs
    @Override
    public Long countBlog() {
        return blogRepository.count();
    }

    //save blog post
    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        if (blog.getId() == null) {
            //save time stamp
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            //initiate view number
            blog.setViews(0);
        } else {
            blog.setUpdateTime(new Date());
        }
        return blogRepository.save(blog);
    }

    //update blog post
    @Transactional
    @Override
    public Blog updateBlog(Long id, Blog blog) {
        Blog b = blogRepository.findOne(id);
        if (b == null) {
            throw new NotFoundException("Post does not exist!");
        }
        //filter out null properties with BeanUtils
        BeanUtils.copyProperties(blog, b, MyBeanUtils.getNullPropertyNames(blog));
        b.setUpdateTime(new Date());
        return blogRepository.save(b);
    }

    //delete blog post
    @Transactional
    @Override
    public void deleteBlog(Long id) {
        //delete blog post from repository
        blogRepository.delete(id);
    }
}
