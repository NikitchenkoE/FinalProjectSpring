package com.example.finalprojectspring.controllers;

import com.example.finalprojectspring.dto.UserEntityDTO;
import com.example.finalprojectspring.entities.Role_Of_Users;
import com.example.finalprojectspring.interfaices.IRegistrationInterf;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Collections;

@Log4j
@Controller
public class RegistrationController {

    private final IRegistrationInterf iRegistrationInterf;

    @Autowired
    public RegistrationController(IRegistrationInterf iRegistrationInterf) {
        this.iRegistrationInterf = iRegistrationInterf;
    }

    @GetMapping("/registration")
    public String registrationForm(Model model) {
        log.info("showed user registration page");
        model.addAttribute("user", new UserEntityDTO());
        return "reg_form_page";
    }

    @PostMapping("/process_register")
    public String processRegister(@ModelAttribute("user") @Valid UserEntityDTO user,
                                  BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "reg_form_page";
        }

        if (iRegistrationInterf.userPresentInDb(user)) {
            log.error("Error user already exist");
            model.addAttribute("errorMessage", "User already exist");
            return "reg_form_page";
        }

        user.setRoles(Collections.singleton(Role_Of_Users.ROLE_USER));
        iRegistrationInterf.addUserToDataBase(user);

        return "redirect:/login";
    }


}
