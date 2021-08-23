package com.example.finalprojectspring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorsPagesController {

    @GetMapping("/error")
    public String errorPage(){
        return "error";
    }

    @GetMapping("/errorNotEnougMoney")
    public String errorPageMoney(){
        return "errorNotEnoughMoney.html";
    }

    @GetMapping("/errorSignUp")
    public String errorSignUp(){
        return "errorSignUp";
    }
}
