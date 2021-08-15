package com.example.finalprojectspring.controllers;

import com.example.finalprojectspring.dto.UserEntityDTO;
import com.example.finalprojectspring.entities.UserEntity;
import com.example.finalprojectspring.interfaices.IGeneraPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class GeneralPageController {

    private final IGeneraPageService iGeneraPageService;

    private String occupation;
    private final String ALL = "all";

    @Autowired
    public GeneralPageController(IGeneraPageService iGeneraPageService) {
        this.iGeneraPageService = iGeneraPageService;
    }

    @GetMapping({"main", "/"})
    public String showMastersOnGeneralPage(Model model, @PageableDefault(sort = {"ID"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return findPaginated(1, model, ALL);
    }

    @GetMapping("/generalpage/{pageNomber}")
    public String findPaginated(@PathVariable(value = "pageNomber") int pageNomber, Model model, @RequestParam(required = false, name = "occupationFilter")
            String occupationFilter) {
        int pageSize = 5;
        Page<UserEntityDTO> page;
        if (occupation == null && occupationFilter == null) {
            page = iGeneraPageService.findPaginated(pageNomber, pageSize, ALL);
        } else {
            if (occupationFilter == null) {
                page = iGeneraPageService.findPaginated(pageNomber, pageSize, occupation);

            } else {
                page = iGeneraPageService.findPaginated(pageNomber, pageSize, occupationFilter);
                occupation = occupationFilter;
            }
        }
        List<UserEntityDTO> listEmployees = page.getContent();
        model.addAttribute("currentPage", pageNomber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listEmployees", listEmployees);
        return "redirect:/general_page";
    }


    @RequestMapping("/login")
    public String loginPage() {
        return "login_page";
    }

    @RequestMapping("/user")
    public String userPage() {
        return "user_page";
    }

    @RequestMapping("/admin")
    public String adminPage() {
        return "admin_page";
    }

    @RequestMapping("/master")
    public String masterPage() {
        return "master_page";
    }


}
