package com.zxy.service;

import com.zxy.po.Comment;

import java.util.List;

public interface CommentService {

    //get comment list by id
    List<Comment> listCommentByBlogId(Long blogId);

    //save comment
    Comment saveComment(Comment comment);
}
