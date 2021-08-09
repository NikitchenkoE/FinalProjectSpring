package com.example.finalprojectspring.controllers;

import com.example.finalprojectspring.dto.UserEntityDTO;
import com.example.finalprojectspring.entities.Role_Of_Users;
import com.example.finalprojectspring.entities.UserEntity;
import com.example.finalprojectspring.interfaices.IAdminPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@Controller
public class AdminPageController {

    private final IAdminPageService iAdminPageService;

    @Autowired
    public AdminPageController(IAdminPageService iAdminPageService) {
        this.iAdminPageService = iAdminPageService;
    }


    @GetMapping("/admin")
    public String showAllUsersWithRoleUser(Model model) {
        List<UserEntity> users = iAdminPageService.findAllwithRoleUser();
        model.addAttribute("showAllUserwihtRoleUser", users);
        return "admin_page";
    }
    @GetMapping("/admin/list/masters")
    public String showAllUsersWithRoleMaster(Model model) {
        List<UserEntity> users = iAdminPageService.findAllWithRoleMaster();
        model.addAttribute("showAllUserWithRoleMaster", users);
        return "masterList";
    }

    @GetMapping("user-delete/{email}")
    public String deleteUserByEmail(@PathVariable("email") String email) {
        iAdminPageService.deleteUserByEmail(email);
        return "redirect:/admin";
    }

    @GetMapping("admin/list/user-delete/{email}")
    public String deleteMasterByEmail(@PathVariable("email") String email) {
        iAdminPageService.deleteUserByEmail(email);
        return "redirect:/admin/list/masters";
    }


    @GetMapping("/admin/add/master")
    public String registrationForm(Model model) {
        model.addAttribute("master", new UserEntityDTO());
        return "addMasterPage";
    }

    @PostMapping("/master_register")
    public String processRegister(@ModelAttribute("master") @Valid UserEntityDTO master,
                                  BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "addMasterPage";
        }

        if (iAdminPageService.userPresentInDb(master)) {
            model.addAttribute("errorMessage", "User already exist");
            return "addMasterPage";
        }

        master.setRoles(Collections.singleton(Role_Of_Users.ROLE_MASTER));
        iAdminPageService.createNewMaster(master);

        return "redirect:/admin";
    }




}
