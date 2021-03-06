package com.zxy.web;
//dependencies

import com.zxy.entity.Type;
import com.zxy.service.BlogService;
import com.zxy.service.TypeService;
import com.zxy.query.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//controller notation
@Controller
public class TypeShowController {

    //declare service classes
    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    //7 blog posts per page, sorted in reverse direction
    @GetMapping("/types/{id}")
    public String types(@PageableDefault(size = 7, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        @PathVariable Long id, Model model) {
        List<Type> types = typeService.listTypeTop(10000);
        if (id == -1) {
            id = types.get(0).getId();
        }
        BlogQuery blogQuery = new BlogQuery();
        blogQuery.setTypeId(id);
        model.addAttribute("types", types);
        model.addAttribute("page", blogService.listBlog(pageable, blogQuery));
        model.addAttribute("activeTypeId", id);
        return "types";
    }
}
