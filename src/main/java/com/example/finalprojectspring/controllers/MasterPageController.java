package com.example.finalprojectspring.controllers;

import com.example.finalprojectspring.dto.ScheduleDTO;
import com.example.finalprojectspring.interfaices.ShowMasterScheduleInterface;
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
public class MasterPageController {

    private final ShowMasterScheduleInterface masterScheduleInterface;
    private String email;

    @Autowired
    public MasterPageController(ShowMasterScheduleInterface masterScheduleInterface) {
        this.masterScheduleInterface = masterScheduleInterface;
    }

    @RequestMapping(value = "/master/page/myMasterpage", method = RequestMethod.GET)
    @ResponseBody
    public void authoriseMasterToMasterPage(Principal principal, HttpServletResponse response) throws IOException {
        email = principal.getName();
        response.sendRedirect("/master/myPages");
    }

    @GetMapping("/master/myPages")
    public String showMastersOnMasterPageeithouMail(Model model, Pageable pageable) {
        log.info("Showed master page");
        return findPaginatedMaster(1, model);
    }

    @GetMapping("/master/myPages/{masterEmail}")
    public String showMastersOnMasterPage(@PathVariable(value = "masterEmail") String masterMail, Model model, @PageableDefault(sort = {"ID"}, direction = Sort.Direction.ASC) Pageable pageable) {
        email = masterMail;
        log.info("Showed master page");
        return findPaginatedMaster(1, model);
    }

    @GetMapping("/master/myPage/{pageNomber}")
    public String findPaginatedMaster(@PathVariable(value = "pageNomber") int pageNo, Model model) {
        int pageSize = 10;
        Page<ScheduleDTO> page = masterScheduleInterface.findPaginatedMasterSchedule(pageNo, pageSize, email);
        List<ScheduleDTO> listSchedule = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listMasterSchedule", listSchedule);
        return "masterPage";
    }

    @GetMapping("/master/page/myMasterpage/{id}")
    public String deleteScheduleByEmail(@PathVariable("id") Long id) {
        ScheduleDTO scheduleDto = ScheduleDTO.builder().
                id(id).build();
        masterScheduleInterface.deleteScheduleDayById(scheduleDto);
        return "redirect:/master/myPages";
    }

    @GetMapping("/master/setDone/firstHour/{sheduleId}")
    public String setDoneFirstHour(@PathVariable(value = "sheduleId") Long scheduleId){
        ScheduleDTO scheduleDto = ScheduleDTO.builder().id(scheduleId).build();
        masterScheduleInterface.setAsDoneFirstHour(scheduleDto);
        return "redirect:/master/myPages";
    }

    @GetMapping("/master/setDone/secondHour/{sheduleId}")
    public String setDoneSecondHour(@PathVariable(value = "sheduleId") Long scheduleId){
        ScheduleDTO scheduleDto = ScheduleDTO.builder().id(scheduleId).build();
        masterScheduleInterface.setAsDoneSecondHour(scheduleDto);
        return "redirect:/master/myPages";
    }

    @GetMapping("/master/setDone/thirdHour/{sheduleId}")
    public String setDoneThirdHour(@PathVariable(value = "sheduleId") Long scheduleId){
        ScheduleDTO scheduleDto = ScheduleDTO.builder().id(scheduleId).build();
        masterScheduleInterface.setAsDoneThirdHour(scheduleDto);
        return "redirect:/master/myPages";
    }

    @GetMapping("/master/setDone/forthHour/{sheduleId}")
    public String setDoneForthHour(@PathVariable(value = "sheduleId") Long scheduleId){
        ScheduleDTO scheduleDto = ScheduleDTO.builder().id(scheduleId).build();
        masterScheduleInterface.setAsDoneForthHour(scheduleDto);
        return "redirect:/master/myPages";
    }





}
