package com.zxy.web;
//dependencies
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//controller class
@Controller
public class ProjectsShowController {

    //direct to about page
    @GetMapping("/projects")
    public String projects() {
        return "projects";
    }
}
