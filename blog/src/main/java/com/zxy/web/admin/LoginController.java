package com.zxy.web.admin;
//dependencies
import com.zxy.po.User;
import com.zxy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin") //global path
public class LoginController {

    @Autowired
    private UserService userService;

    //direct to login page
    @GetMapping
    public String loginPage() {
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, //param
                        @RequestParam String password, //param
                        HttpSession session,
                        RedirectAttributes attributes) {

        User user = userService.checkUser(username, password);
        //login info is correct
        if (user != null) {
            user.setPassword(null);//dont send password to front end for security reasons
            session.setAttribute("user",user);
            //go to admin index page
            return "admin/index";
        } else { ////login info incorrect
            attributes.addFlashAttribute("message", "Username or Password Incorrect!");
            return "redirect:/admin";
        }
    }

    //log out
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/admin";
    }

}
