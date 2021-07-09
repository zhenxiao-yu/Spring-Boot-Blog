package com.zxy.web;
//dependencies
import com.zxy.po.Comment;
import com.zxy.po.User;
import com.zxy.service.BlogService;
import com.zxy.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

//controller class
@Controller
public class CommentController {

    //declare service classes
    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    @Value("${comment.avatar}")
    private String avatar;

    //direct to corresponding comment position
    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model) {
        model.addAttribute("comments", commentService.listCommentByBlogId(blogId));
        return "blog :: commentList";
    }

    //post comment
    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session) {
        Long blogId = comment.getBlog().getId();
        comment.setBlog(blogService.getBlog(blogId));
        User user = (User) session.getAttribute("user");
        if (user != null) {
            //set commenter's avatar
            comment.setAvatar(user.getAvatar());
            //is Admin = true
            comment.setAdminComment(true);
        } else {
            comment.setAvatar(avatar);
        }
        //save comment
        commentService.saveComment(comment);
        return "redirect:/comments/" + blogId;
    }
}
