package com.example.finalprojectspring.controllers;

import com.example.finalprojectspring.dto.MoneyDTO;
import com.example.finalprojectspring.interfaices.MoneyServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;

@Controller
public class UserPageController {
    private String userEmail;


    private final MoneyServiceInterface moneyServiceInterface;

    @Autowired
    public UserPageController(MoneyServiceInterface moneyServiceInterface) {
        this.moneyServiceInterface = moneyServiceInterface;
    }

    @RequestMapping(value = "/user/page/myUserPage", method = RequestMethod.GET)
    @ResponseBody
    public void authoriseMasterToMasterPage(Principal principal, HttpServletResponse response) throws IOException {
        userEmail = principal.getName();
        response.sendRedirect("/user/myPage");
    }

    @GetMapping("/user/myPage")
    public String myPage(Model model, MoneyDTO moneyDTO){
        moneyDTO = moneyServiceInterface.showUserByEmail(userEmail);
        model.addAttribute("user", moneyDTO);
        return "userPage";
    }

    @PostMapping("/processAddMoney")
    public String processRegister(@ModelAttribute("user") @Valid MoneyDTO user,
                                  BindingResult bindingResultUserPage) {
        if (bindingResultUserPage.hasErrors()) {
            return "userPage";
        }
        user.setEmail(userEmail);
        moneyServiceInterface.replenishMoneyAccount(user);

        return "redirect:/user/myPage";
    }

}
