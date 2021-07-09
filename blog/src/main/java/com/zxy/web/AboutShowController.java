package com.zxy.web;
//dependencies
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//controller class
@Controller
public class AboutShowController {
    //direct to about page
    @GetMapping("/about")
    public String about() {
        return "about";
    }
}
