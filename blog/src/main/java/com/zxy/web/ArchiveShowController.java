package com.zxy.web;
//dependencies
import com.zxy.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//controller class
@Controller
public class ArchiveShowController {

    //declare service classes
    @Autowired
    private BlogService blogService;

    //direct to archives page
    @GetMapping("/archives")
    public String archives(Model model) {
        model.addAttribute("archiveMap", blogService.archiveBlog());
        model.addAttribute("blogCount", blogService.countBlog());
        return "archives";
    }
}
