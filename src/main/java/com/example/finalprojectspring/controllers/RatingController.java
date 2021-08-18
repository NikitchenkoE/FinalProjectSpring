package com.example.finalprojectspring.controllers;

import com.example.finalprojectspring.dto.RatingDTO;
import com.example.finalprojectspring.interfaices.InterfaceUserMarkService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@Log4j
public class RatingController {

    private final InterfaceUserMarkService markService;

    private String email;

    @Autowired
    public RatingController(InterfaceUserMarkService markService) {
        this.markService = markService;
    }

    @GetMapping("/user/ratingMaste")
    public String ratingPage(Model model){
        model.addAttribute("markMaster", new RatingDTO());
        return "ratingPage";
    }


    @GetMapping("/user/ratingMaster/{email}")
    public String ratingForm(Model model, @PathVariable(value = "email") String masterEmail){
        log.info("showed rating page");
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
