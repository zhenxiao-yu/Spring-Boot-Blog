package com.zxy.web;

import com.zxy.NotFoundException;
import com.zxy.service.BlogService;
import com.zxy.service.TagService;
import com.zxy.service.TypeService;
import com.zxy.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    //Declare service classes
    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    //Normal Display
    //7 blog posts per page, sorted in reverse direction
    @GetMapping("/")
    public String index(@PageableDefault(size = 7, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        Model model) {
        model.addAttribute("page",blogService.listBlog(pageable));
        //display categories in a list
        model.addAttribute("types", typeService.listTypeTop(6));
        //display tags in a list
        model.addAttribute("tags", tagService.listTagTop(10));
        //display recent posts in recommendation area
        model.addAttribute("recommendBlogs", blogService.listRecommendBlogTop(6));
        return "index";
    }

    //Search Result Display
    //7 blog posts per page, sorted in reverse direction
    @PostMapping("/search")
    public String search(@PageableDefault(size = 7, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         @RequestParam String query, Model model) {
        model.addAttribute("page", blogService.listBlog("%"+query+"%", pageable));
        model.addAttribute("query", query);
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String blog() {
        return "blog";
    }
}
