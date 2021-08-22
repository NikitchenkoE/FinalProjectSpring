package com.example.finalprojectspring.controllers;

import com.example.finalprojectspring.dto.ScheduleDTO;
import com.example.finalprojectspring.interfaices.SetScheduleToMasterServiceInterface;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;

@Controller
@Log4j
public class SetScheduleToMasterController {

    private String masterEmail;
    private final SetScheduleToMasterServiceInterface masterServiceInterface;

    @Autowired
    public SetScheduleToMasterController(SetScheduleToMasterServiceInterface masterServiceInterface) {
        this.masterServiceInterface = masterServiceInterface;
    }

    @GetMapping("/admin/add/schedule/")
    public String showAddSchedulePage(Model model){
        model.addAttribute("addSchedule", new ScheduleDTO());
        return "addScheduleToMasterPage";
    }

    @GetMapping("/admin/add/schedule/{masterEmail}")
    public String addScheduleToMasterPage(@PathVariable(value = "masterEmail") String email, Model model){
        log.info("Showed page to add schedule");
        masterEmail = email;
        model.addAttribute("addSchedule", new ScheduleDTO());
        return "addScheduleToMasterPage";
    }

    @PostMapping("/admin/addDay")
    public String processRegister(@ModelAttribute("addSchedule") @Valid ScheduleDTO scheduleDto,
                                  BindingResult bindingResult, Model model,@RequestParam(required = false, name = "workDay")
                                              String workDay) throws ParseException {
        log.info("Showed page to add schedule");
        scheduleDto.setMasterEmail(masterEmail);
        scheduleDto.setWorkDay(workDay);

        if (bindingResult.hasErrors()) {
            return "addScheduleToMasterPage";
        }

        if (masterServiceInterface.dateToMasterPresentInDb(scheduleDto)) {
            log.error("Error date already exist");
            model.addAttribute("errorMessage", "Date to master already exist");
            return "addScheduleToMasterPage";
        }

        scheduleDto.setFirstHour(true);
        scheduleDto.setSecondHour(true);
        scheduleDto.setThirdHour(true);
        scheduleDto.setForthHour(true);
        masterServiceInterface.setWorkDayToMaster(scheduleDto);

        return "redirect:/admin/list/masters";
    }



}
