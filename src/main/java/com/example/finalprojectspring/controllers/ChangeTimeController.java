package com.example.finalprojectspring.controllers;

import com.example.finalprojectspring.dto.ScheduleDTO;
import com.example.finalprojectspring.exeption.NotEnoughMoneyException;
import com.example.finalprojectspring.interfaices.SignUpServiceInterface;
import com.example.finalprojectspring.interfaices.ChangeBookingTimeServiceInterface;
import lombok.extern.log4j.Log4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@Log4j
public class ChangeTimeController {
    private final ChangeBookingTimeServiceInterface changeBookingTimeServiceInterface;
    private final SignUpServiceInterface signUpServiceInterface;
    private ScheduleDTO masterSchedule;
    private String clientEmail;

    public ChangeTimeController(ChangeBookingTimeServiceInterface changeBookingTimeServiceInterface, SignUpServiceInterface signUpServiceInterface) {
        this.changeBookingTimeServiceInterface = changeBookingTimeServiceInterface;
        this.signUpServiceInterface = signUpServiceInterface;
    }

    @GetMapping("/admin/changeTime")
    public String showScheduleOnSignUpPage(Model model, @PageableDefault(sort = {"ID"}, direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("Showed changeTime page");
        return findPaginatedMaster(1, model);
    }

    @GetMapping("/admin/changeTime/{pageNomber}")
    public String findPaginatedMaster(@PathVariable(value = "pageNomber") int pageNo, Model model) {
        int pageSize = 10;
        Page<ScheduleDTO> page = signUpServiceInterface.findPaginatedMasterSchedule(pageNo, pageSize, masterSchedule.getMasterEmail());
        List<ScheduleDTO> listSchedule = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listMasterSchedule", listSchedule);
        return "changeTimePage";
    }

    @GetMapping("/admin/changeFirstHour/{id}")
    public String changeFirstHour(@PathVariable (value = "id") Long id, Model model) throws NotEnoughMoneyException {
        ScheduleDTO scheduleDTO = changeBookingTimeServiceInterface.findById(id);
        masterSchedule = scheduleDTO;
        clientEmail = masterSchedule.getClientEmailFirstHour();
        changeBookingTimeServiceInterface.setFreeFirstHour(masterSchedule);
        return "redirect:/admin/changeTime";
    }

    @GetMapping("/admin/changeSecondHour/{id}")
    public String changeSecondHour(@PathVariable (value = "id") Long id, Model model) throws NotEnoughMoneyException {
        ScheduleDTO scheduleDTO = changeBookingTimeServiceInterface.findById(id);
        masterSchedule = scheduleDTO;
        clientEmail = masterSchedule.getClientEmailSecondHour();
        changeBookingTimeServiceInterface.setFreeSecondHour(masterSchedule);
        return "redirect:/admin/changeTime";
    }

    @GetMapping("/admin/changeThirdHour/{id}")
    public String changeThirdHour(@PathVariable (value = "id") Long id, Model model) throws NotEnoughMoneyException {
        ScheduleDTO scheduleDTO = changeBookingTimeServiceInterface.findById(id);
        masterSchedule = scheduleDTO;
        clientEmail = masterSchedule.getClientEmailThirdHour();
        changeBookingTimeServiceInterface.setFreeThirdHour(masterSchedule);
        return "redirect:/admin/changeTime";
    }

    @GetMapping("/admin/changeForthHour/{id}")
    public String changeForthHour(@PathVariable (value = "id") Long id, Model model) throws NotEnoughMoneyException {
        ScheduleDTO scheduleDTO = changeBookingTimeServiceInterface.findById(id);
        masterSchedule = scheduleDTO;
        clientEmail = masterSchedule.getClientEmailForthHour();
        changeBookingTimeServiceInterface.setFreeForthHour(masterSchedule);
        return "redirect:/admin/changeTime";
    }

    @GetMapping("/admin/signUp/firstHour/{scheduleId}")
    public String signUpFirstHour(@PathVariable(value = "scheduleId") Long scheduleId) throws NotEnoughMoneyException {
        ScheduleDTO scheduleDto = ScheduleDTO.builder()
                .id(scheduleId)
                .clientEmailFirstHour(clientEmail)
                .build();

        if (signUpServiceInterface.firstHourAlreadyBooked(scheduleDto)) {
            log.error("Error date already booked");
            return "errorSignUp";
        }
        signUpServiceInterface.setFirstHour(scheduleDto);
        return "redirect:/admin/changeTime";
    }

    @GetMapping("/admin/signUp/secondHour/{scheduleId}")
    public String signUpSecondHour(@PathVariable(value = "scheduleId") Long scheduleId) throws NotEnoughMoneyException {
        ScheduleDTO scheduleDto = ScheduleDTO.builder()
                .id(scheduleId)
                .clientEmailSecondHour(clientEmail)
                .build();
        if (signUpServiceInterface.secondHourAlreadyBooked(scheduleDto)) {
            log.error("Error date already booked");
            return "errorSignUp";
        }
        signUpServiceInterface.setSecondHour(scheduleDto);
        return "redirect:/user/signUpPage";
    }

    @GetMapping("/admin/signUp/thirdHour/{scheduleId}")
    public String signUpThirdHour(@PathVariable(value = "scheduleId") Long scheduleId) throws NotEnoughMoneyException {
        ScheduleDTO scheduleDto = ScheduleDTO.builder()
                .id(scheduleId)
                .clientEmailThirdHour(clientEmail)
                .build();
        if (signUpServiceInterface.thirdHourAlreadyBooked(scheduleDto)) {
            log.error("Error date already booked");
            return "errorSignUp";
        }
        signUpServiceInterface.setThirdHour(scheduleDto);
        return "redirect:/user/signUpPage";
    }

    @GetMapping("/admin/signUp/forthHour/{scheduleId}")
    public String signUpForthHour(@PathVariable(value = "scheduleId") Long scheduleId) throws NotEnoughMoneyException {
        ScheduleDTO scheduleDto = ScheduleDTO.builder()
                .id(scheduleId)
                .clientEmailForthHour(clientEmail)
                .build();
        if (signUpServiceInterface.forthHourAlreadyBooked(scheduleDto)) {
            log.error("Error date already booked");
            return "errorSignUp";
        }
        signUpServiceInterface.setForthHour(scheduleDto);
        return "redirect:/user/signUpPage";
    }


}
