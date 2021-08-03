package com.example.finalprojectspring.Controllers;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GeneralPageController {

    @RequestMapping({"/","main"})
    public String mainPage() {
        return "general_page";
    }

    @RequestMapping("/login")
    public String loginPage() {
        return "login_page";
    }

    @RequestMapping("/user_page")
    public String userPage(){
        return "user_page";
    }


}
