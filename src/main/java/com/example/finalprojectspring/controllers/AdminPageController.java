package com.example.finalprojectspring.controllers;

import com.example.finalprojectspring.dto.UserEntityDTO;
import com.example.finalprojectspring.entities.RoleOfUsers;
import com.example.finalprojectspring.entities.UserEntity;
import com.example.finalprojectspring.interfaices.IAdminPageService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@Controller
@Log4j
public class AdminPageController {

    private final IAdminPageService iAdminPageService;

    @Autowired
    public AdminPageController(IAdminPageService iAdminPageService) {
        this.iAdminPageService = iAdminPageService;
    }


    @GetMapping("/admin")
    public String showAllUsersWithRoleUser(Model model,
                                           @PageableDefault(sort = {"ID"}, direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("Showed admin page");
        return findPaginatedUser(1, model);
    }

    @GetMapping("/admin/adminPageUsers/{pageNomber}")
    public String findPaginatedUser(@PathVariable(value = "pageNomber") int pageNo, Model model) {
        int pageSize = 5;
        Page<UserEntity> page = iAdminPageService.findPaginatedUser(pageNo, pageSize);
        List<UserEntity> listUsers = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listUsers", listUsers);
        return "admin_page";
    }


    @GetMapping("/admin/list/masters")
    public String showAllUsersWithRoleMaster(Model model,
                                             @PageableDefault(sort = {"ID"}, direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("Showed page with list of masters");
        return findPaginatedMaster(1, model);
    }

    @GetMapping("/admin/adminPageMasters/{pageNomber}")
    public String findPaginatedMaster(@PathVariable(value = "pageNomber") int pageNo, Model model) {
        int pageSize = 5;
        Page<UserEntity> page = iAdminPageService.findPaginatedMaster(pageNo, pageSize);
        List<UserEntity> listMasters = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listMasters", listMasters);
        return "masterList";
    }


    @GetMapping({"admin/user-delete/{email}", "admin/adminPageUsers/admin/user-delete/{email}"})
    public String deleteUserByEmail(@PathVariable("email") String email) {
        iAdminPageService.deleteUserByEmail(email);
        return "redirect:/admin";
    }

    @GetMapping({"/admin/adminPageMasters/user-delete/{email}", "admin/list/user-delete/{email}"})
    public String deleteMasterByEmail(@PathVariable("email") String email) {
        iAdminPageService.deleteUserByEmail(email);
        return "redirect:/admin/list/masters";
    }


    @GetMapping("/admin/add/master")
    public String registrationForm(Model model) {
        log.info("showed master registration page");
        model.addAttribute("master", new UserEntityDTO());
        return "addMasterPage";
    }

    @PostMapping("/admin/master_register")
    public String processRegister(@ModelAttribute("master") @Valid UserEntityDTO master,
                                  BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            log.error("Error in validation on master registration page");
            return "addMasterPage";
        }

        if (iAdminPageService.userPresentInDb(master)) {
            model.addAttribute("errorMessage", "User already exist");
            return "addMasterPage";
        }

        master.setRoles(Collections.singleton(RoleOfUsers.ROLE_MASTER));
        master.setMoney(0.0);
        iAdminPageService.createNewMaster(master);

        return "redirect:/admin";
    }


}
