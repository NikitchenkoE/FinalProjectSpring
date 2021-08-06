package com.example.finalprojectspring.controllers;

import com.example.finalprojectspring.dto.UserEntityDTO;
import com.example.finalprojectspring.entities.Role_Of_Users;
import com.example.finalprojectspring.entities.UserEntity;
import com.example.finalprojectspring.service.IRegistrationInterf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;


@Controller
public class RegistrationController {

private final IRegistrationInterf iRegistrationInterf;

    @Autowired
    public RegistrationController(IRegistrationInterf iRegistrationInterf) {
        this.iRegistrationInterf = iRegistrationInterf;
    }

    @GetMapping("/registration")
    public String registrationForm(Model model) {
        model.addAttribute("user",new UserEntityDTO());
        return "reg_form_page";
    }

    @PostMapping("/process_register")
    public String processRegister(UserEntityDTO user) {
        user.setRoles(Collections.singleton(Role_Of_Users.ROLE_USER));
        iRegistrationInterf.addUserToDataBase(user);

        return "login_page";
    }


}
