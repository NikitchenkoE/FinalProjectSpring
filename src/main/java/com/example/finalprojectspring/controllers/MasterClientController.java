package com.example.finalprojectspring.controllers;

import com.example.finalprojectspring.entities.MasterClient;
import com.example.finalprojectspring.entities.UserEntity;
import com.example.finalprojectspring.service.InterfaceMasteClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MasterClientController {

    private final InterfaceMasteClient masteClient;

    @Autowired
    public MasterClientController(InterfaceMasteClient masteClient) {
        this.masteClient = masteClient;
    }

    @GetMapping("/signUp_page")
    public String showpage(){
        return "signUp_page";
    }

    @GetMapping("/signtomaster/{email}")
    public String signToMasterPageForm(@PathVariable(value = "email") String email, Model model) {
        MasterClient client = new MasterClient();
        model.addAttribute("client",client);
        model.addAttribute("master",masteClient.findUserInDbByEmail(email));
        return "signUp_page";
    }

    @PostMapping
    public String saveToMaster(@ModelAttribute ("client") MasterClient client,
                               @ModelAttribute("master") UserEntity userEntity){
        masteClient.addNewClient(userEntity,client);
        return "general_page";
    }
}
