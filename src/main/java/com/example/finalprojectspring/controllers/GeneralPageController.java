package com.example.finalprojectspring.controllers;

import org.springframework.stereotype.Controller;

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

    @RequestMapping("/user")
    public String userPage(){
        return "user_page";
    }

    @RequestMapping("/admin")
    public String adminPage(){
        return "admin_page";
    }

    @RequestMapping("/master")
    public String masterPage(){
        return "master_page";
    }


}
