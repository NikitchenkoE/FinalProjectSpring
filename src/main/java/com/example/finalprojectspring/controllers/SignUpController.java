package com.example.finalprojectspring.controllers;

import com.example.finalprojectspring.dto.ScheduleDTO;
import com.example.finalprojectspring.exeption.NotEnoughMoneyException;
import com.example.finalprojectspring.interfaices.SignUpServiceInterface;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@Log4j
public class SignUpController {

    private String masterEmail;
    private String userEmail;

    private final SignUpServiceInterface signUpServiceInterface;

    @Autowired
    public SignUpController(SignUpServiceInterface signUpServiceInterface) {
        this.signUpServiceInterface = signUpServiceInterface;
    }

    @RequestMapping(value = "/user/signUp/{masterEmail}", method = RequestMethod.GET)
    @ResponseBody
    public void authoriseUserToSignUpPage(@PathVariable(value = "masterEmail") String email,
                                          Principal principal,
                                          HttpServletResponse response) throws IOException {
        masterEmail = email;
        userEmail = principal.getName();
        response.sendRedirect("/user/signUpPage");
    }

    @GetMapping("/user/signUpPage")
    public String showScheduleOnSignUpPage(Model model, @PageableDefault(sort = {"ID"}, direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("Showed signUp page");
        return findPaginatedMaster(1, model);
    }

    @GetMapping("/user/signUpPages/{pageNomber}")
    public String findPaginatedMaster(@PathVariable(value = "pageNomber") int pageNo, Model model) {
        int pageSize = 10;
        Page<ScheduleDTO> page = signUpServiceInterface.findPaginatedMasterSchedule(pageNo, pageSize, masterEmail);
        List<ScheduleDTO> listSchedule = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listMasterSchedule", listSchedule);
        return "signUp";
    }

    @GetMapping("/user/signUp/firstHour/{scheduleId}")
    public String signUpFirstHour(@PathVariable(value = "scheduleId") Long scheduleId) throws NotEnoughMoneyException {
        ScheduleDTO scheduleDto = ScheduleDTO.builder()
                .id(scheduleId)
                .clientEmailFirstHour(userEmail)
                .build();

        if (signUpServiceInterface.firstHourAlreadyBooked(scheduleDto)) {
            log.error("Error date already booked");
            return "errorSignUp";
        }
        signUpServiceInterface.setFirstHour(scheduleDto);
        return "redirect:/user/signUpPage";
    }

    @GetMapping("/user/signUp/secondHour/{scheduleId}")
    public String signUpSecondHour(@PathVariable(value = "scheduleId") Long scheduleId) throws NotEnoughMoneyException {
        ScheduleDTO scheduleDto = ScheduleDTO.builder()
                .id(scheduleId)
                .clientEmailSecondHour(userEmail)
                .build();
        if (signUpServiceInterface.secondHourAlreadyBooked(scheduleDto)) {
            log.error("Error date already booked");
            return "errorSignUp";
        }
        signUpServiceInterface.setSecondHour(scheduleDto);
        return "redirect:/user/signUpPage";
    }

    @GetMapping("/user/signUp/thirdHour/{scheduleId}")
    public String signUpThirdHour(@PathVariable(value = "scheduleId") Long scheduleId) throws NotEnoughMoneyException {
        ScheduleDTO scheduleDto = ScheduleDTO.builder()
                .id(scheduleId)
                .clientEmailThirdHour(userEmail)
                .build();
        if (signUpServiceInterface.thirdHourAlreadyBooked(scheduleDto)) {
            log.error("Error date already booked");
            return "errorSignUp";
        }
        signUpServiceInterface.setThirdHour(scheduleDto);
        return "redirect:/user/signUpPage";
    }

    @GetMapping("/user/signUp/forthHour/{scheduleId}")
    public String signUpForthHour(@PathVariable(value = "scheduleId") Long scheduleId) throws NotEnoughMoneyException {
        ScheduleDTO scheduleDto = ScheduleDTO.builder()
                .id(scheduleId)
                .clientEmailForthHour(userEmail)
                .build();
        if (signUpServiceInterface.forthHourAlreadyBooked(scheduleDto)) {
            log.error("Error date already booked");
            return "errorSignUp";
        }
        signUpServiceInterface.setForthHour(scheduleDto);
        return "redirect:/user/signUpPage";
    }

}
