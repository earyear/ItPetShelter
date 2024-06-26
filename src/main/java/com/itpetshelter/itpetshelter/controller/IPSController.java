package com.itpetshelter.itpetshelter.controller;

import com.itpetshelter.itpetshelter.domain.Reservation;
import com.itpetshelter.itpetshelter.dto.ReservationDTO;
import com.itpetshelter.itpetshelter.repository.AnimalRepository;
import com.itpetshelter.itpetshelter.repository.ShelterRepository;
import com.itpetshelter.itpetshelter.service.ReservationService;
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

@Controller
@RequestMapping("/itpetshelter")
@Log4j2
@RequiredArgsConstructor
public class IPSController {

    private final AnimalRepository animalRepository;
    private final ShelterRepository shelterRepository;
    private final ReservationService reservationService;

    @GetMapping("/index")
    public void index() {
    }

    @GetMapping("/reservation")
    public String showReservationForm(Model model) {
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("animals", animalRepository.findAll());
        model.addAttribute("shelters", shelterRepository.findAll());
        return "itpetshelter/reservation";
    }

    @PostMapping("/reservation")
//    public String submitReservation(@ModelAttribute("reservation") Reservation reservation, RedirectAttributes redirectAttributes) {
    public String submitReservation(ReservationDTO reservationDTO, RedirectAttributes redirectAttributes) {
        log.info("reservationDTO: " + reservationDTO);
        Reservation reservation = reservationService.saveReservation2(reservationDTO);
        log.info("IPSController 반환 후, 결과 확인 : " + reservation);

        redirectAttributes.addAttribute("Rdate", reservation.getRdate());
        redirectAttributes.addAttribute("Rtime", reservation.getRtime());
        redirectAttributes.addAttribute("Aname", reservation.getAnimal().getAname());
        redirectAttributes.addAttribute("Sname", reservation.getShelter().getSname());

        return "redirect:/itpetshelter/reservation_success";
    }

    @GetMapping("/reservation_success")
    public void showReservationSuccess(@RequestParam("Rdate") LocalDate Rdate, @RequestParam("Rtime") String Rtime,
                                         @RequestParam("Aname") String  Aname, @RequestParam("Sname") String Sname, Model model) {
        model.addAttribute("Rdate", Rdate);
        model.addAttribute("Rtime", Rtime);
        model.addAttribute("Aname", Aname);
        model.addAttribute("Sname", Sname);
    }

    @GetMapping("/shelter")
    public String shelter() {
        return "page/shelter";
    }

    @GetMapping("/volunteer")
    public String volunteer() {
        return "page/volunteer";
    }


}