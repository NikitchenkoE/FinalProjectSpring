package com.example.finalprojectspring.controllers;

import com.example.finalprojectspring.dto.UserEntityDTO;
import com.example.finalprojectspring.interfaices.IGeneraPageService;
import lombok.extern.log4j.Log4j;
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
@Log4j
public class GeneralPageController {

    private final IGeneraPageService iGeneraPageService;

    private String occupation;
    private String sortField = "ratings";
    private String sortDir = "asc";
    private final String ALL = "all";

    @Autowired
    public GeneralPageController(IGeneraPageService iGeneraPageService) {
        this.iGeneraPageService = iGeneraPageService;
    }

    @GetMapping({"main", "/"})
    public String showMastersOnGeneralPage(Model model, @PageableDefault(sort = {"ID"}, direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("Showed general page");
        return findPaginated(1, model, ALL, sortField, sortDir);
    }


    @GetMapping("/generalpage/{pageNomber}")
    public String findPaginated(@PathVariable(value = "pageNomber") int pageNomber, Model model, @RequestParam(required = false, name = "occupationFilter")
            String occupationFilter, @RequestParam(value = "sortField", required = false) String sortField, @RequestParam(value = "sortDir", required = false) String sortDir) {
        int pageSize = 5;
        Page<UserEntityDTO> page;

        if (sortField == null) {
            sortField = "firstName";
            sortDir = "asc";
        }

        if (occupation == null && occupationFilter == null) {
            page = iGeneraPageService.findPaginated(pageNomber, pageSize, ALL, sortField, sortDir);
        } else {
            if (occupationFilter == null) {
                page = iGeneraPageService.findPaginated(pageNomber, pageSize, occupation, sortField, sortDir);

            } else {
                page = iGeneraPageService.findPaginated(pageNomber, pageSize, occupationFilter, sortField, sortDir);
                occupation = occupationFilter;
            }
        }
        List<UserEntityDTO> listEmployees = page.getContent();
        model.addAttribute("currentPage", pageNomber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listEmployees", listEmployees);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "general_page";
    }

    @RequestMapping("/login")
    public String loginPage() {
        log.info("Showed login page");
        return "login_page";
    }


}
