package com.zxy.service;
//dependencies

import com.zxy.entity.Comment;

import java.util.List;


public interface CommentService {
    //list of comments sorted by id
    List<Comment> listCommentByBlogId(Long blogId);

    //save comment
    Comment saveComment(Comment comment);
}
