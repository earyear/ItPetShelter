package com.itpetshelter.itpetshelter.controller;

import com.itpetshelter.itpetshelter.domain.Reservation;
import com.itpetshelter.itpetshelter.domain.Volunteer;
import com.itpetshelter.itpetshelter.dto.ReservationDTO;
import com.itpetshelter.itpetshelter.dto.ShelterDTO;
import com.itpetshelter.itpetshelter.dto.VolunteerDTO;
import com.itpetshelter.itpetshelter.service.ShelterService;
import com.itpetshelter.itpetshelter.service.VolunteerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/page")
@Log4j2
@RequiredArgsConstructor

public class ShelterController {

    private final ShelterService shelterService;
    private final VolunteerService volunteerService;

    @GetMapping("/shelter")
    public void shelter(Model model) {
        List<ShelterDTO> shelterlist = shelterService.getAllshelters();
        log.info("ShelterController shelterlist :"+shelterlist);
        model.addAttribute("shelterlist", shelterlist);
    }

    @GetMapping("/volunteer")
    public void volunteer(Model model) {
        List<VolunteerDTO> volunteerlist = shelterService.getAllvolunteers();
        model.addAttribute("volunteerlist", volunteerlist);
        log.info("ShelterController volunteerlist : "+volunteerlist);
        List<ShelterDTO> shelterlist = shelterService.getAllshelters();
        model.addAttribute("shelterlist", shelterlist);
        log.info("ShelterController shelterlist : "+shelterlist);
    }

    //    0627 수정
    @PostMapping("/volunteer")
    public String submitVolunteer(VolunteerDTO volunteerDTO, RedirectAttributes redirectAttributes) {
        log.info("volunteerDTO: " + volunteerDTO);
        Volunteer volunteer = volunteerService.saveVolunteer( volunteerDTO);
        log.info("Controller 반환 후, 결과 확인 : " +  volunteer);

        redirectAttributes.addAttribute("Vdate",  volunteer.getVdate());
        redirectAttributes.addAttribute("Vtime",  volunteer.getVtime());
        redirectAttributes.addAttribute("Sname",  volunteer.getShelter().getSname());

        return "redirect:/page/volunteer_success";
    }

    @GetMapping("/volunteer_success")
    public void showVolunteerSuccess(@RequestParam("Vdate") LocalDate Vdate, @RequestParam("Vtime") String Vtime,
                                     @RequestParam("Sname") String Sname, Model model) {
        model.addAttribute("Vdate", Vdate);
        model.addAttribute("Vtime", Vtime);
        model.addAttribute("Sname", Sname);
    }






}
