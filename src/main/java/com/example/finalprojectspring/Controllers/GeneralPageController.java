package com.example.finalprojectspring.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GeneralPageController {

    @RequestMapping({"main","/","general"})
    public String mainPage(){
        return "general_page.html";
    }

    @RequestMapping("main/login")
    public String loginPage(){
        return "login_page.html";
    }

    @RequestMapping("main/registration")
    public String registrationForm(){
        return "reg_form_page.html";
    }
}
