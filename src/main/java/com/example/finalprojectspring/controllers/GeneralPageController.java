package com.example.finalprojectspring.controllers;

import com.example.finalprojectspring.entities.UserEntity;
import com.example.finalprojectspring.service.IGeneraPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class GeneralPageController {

    private final IGeneraPageService iGeneraPageService;

    @Autowired
    public GeneralPageController(IGeneraPageService iGeneraPageService) {
        this.iGeneraPageService = iGeneraPageService;
    }

    @RequestMapping({"/","main"})
    public String mainPage() {
        return "general_page";
    }

    @GetMapping({"main","/"})
    public String showMastersOnGeneralPage (Model model){
        List<UserEntity> masters = iGeneraPageService.showAllMasters();
        model.addAttribute("showAllMasters", masters);
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
