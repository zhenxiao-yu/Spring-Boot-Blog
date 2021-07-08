package com.zxy.dao;

import com.zxy.po.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment,Long>{

    //find a comment s based on the blog and the parent comment its associated with
    List<Comment> findByBlogIdAndParentCommentNull(Long blogId, Sort sort);
}
