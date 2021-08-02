package com.example.finalprojectspring.Controllers;

import com.example.finalprojectspring.Entities.Role_Of_Users;
import com.example.finalprojectspring.Entities.UserEntity;
import com.example.finalprojectspring.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;


@Controller
public class RegistrationController {

  private final UserRepository userRepository;

    @Autowired
    public RegistrationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/registration")
    public String registrationForm(Model model) {
        model.addAttribute("user",new UserEntity());
        return "reg_form_page";
    }

    @PostMapping("/process_register")
    public String processRegister(UserEntity user) {
        user.setRoles(Collections.singleton(Role_Of_Users.ROLE_USER));
        userRepository.save(user);

        return "login_page";
    }


}
