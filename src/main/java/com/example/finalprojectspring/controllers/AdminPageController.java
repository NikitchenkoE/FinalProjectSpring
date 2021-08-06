package com.example.finalprojectspring.controllers;

import com.example.finalprojectspring.dto.UserEntityDTO;
import com.example.finalprojectspring.entities.UserEntity;
import com.example.finalprojectspring.service.IAdminPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class AdminPageController {

    private final IAdminPageService iAdminPageService;

    @Autowired
    public AdminPageController(IAdminPageService iAdminPageService) {
        this.iAdminPageService = iAdminPageService;
    }


    @GetMapping("/admin")
    public String showAllUsersWithRoleUser(Model model){
        List<UserEntity> users= iAdminPageService.findAllwithRoleUser();
        model.addAttribute("showAllUserwihtRoleUser",users);
        return "admin_page";
    }

    @GetMapping("user-delete/{email}")
    public String deleteUserByEmail(@PathVariable("email") String email){
        iAdminPageService.deleteUserByEmail(email);
        return "redirect:/admin";
    }



}
