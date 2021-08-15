package com.example.finalprojectspring.controllers;

import com.example.finalprojectspring.dto.RatingDTO;
import com.example.finalprojectspring.interfaices.InterfaceUserMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class RatingController {

    private final InterfaceUserMarkService markService;

    private String email;

    @Autowired
    public RatingController(InterfaceUserMarkService markService) {
        this.markService = markService;
    }

    @GetMapping("/ratingMaster/{email}")
    public String ratingForm(Model model, @PathVariable(value = "email") String masterEmail){
        email=masterEmail;
        model.addAttribute("markMaster", new RatingDTO());
        return "ratingPage";
    }

    @PostMapping("/sentMark")
    public String processRegister( @ModelAttribute("markMaster")  RatingDTO ratingDTO) {
        markService.sentMarkToMaster(ratingDTO,email);
        return "redirect:/main";
    }

}