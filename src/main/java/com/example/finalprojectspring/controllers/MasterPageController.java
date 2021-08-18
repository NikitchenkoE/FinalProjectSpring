package com.example.finalprojectspring.controllers;

import com.example.finalprojectspring.dto.ScheduleDto;
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
        Page<ScheduleDto> page = masterScheduleInterface.findPaginatedMasterSchedule(pageNo, pageSize, email);
        List<ScheduleDto> listMasters = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listMasterSchedule", listMasters);
        return "masterPage";
    }
//TODO chek bag with delete (cant delete in cause of foreign key)
    @GetMapping("/master/page/myMasterpage/{id}")
    public String deleteScheduleByEmail(@PathVariable("id") Long id) {
        ScheduleDto scheduleDto = ScheduleDto.builder().
                id(id).build();
        masterScheduleInterface.deleteScheduleDayById(scheduleDto);
        return "redirect:/master/myPages";
    }


}
