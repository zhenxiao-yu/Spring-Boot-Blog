package com.zxy.dao;

import com.zxy.po.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*Repository Interface*/

//extends JpaSpecificationExecutor for dynamic searching
public interface CommentRepository extends JpaRepository<Comment, Long> {
    //find comment based on blog id and parent comment
    //blog id refers to the exact post the comment is posted under
    //parent comment is the comment that the target comment is replying to (no parent comment = new comment)
    List<Comment> findByBlogIdAndParentCommentNull(Long blogId, Sort sort);
}
