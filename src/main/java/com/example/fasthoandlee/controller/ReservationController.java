package com.example.fasthoandlee.controller;

import com.example.fasthoandlee.domain.Reservation;
import com.example.fasthoandlee.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping
    public String getAllReservations(Model model) {
        model.addAttribute("reservations", reservationService.findAllReservations());
        return "reservations/list";
    }

    @GetMapping("/new")
    public String showReservationForm(Model model) {
        model.addAttribute("reservation", new Reservation());
        return "reservations/form";
    }

    @PostMapping
    public String createReservation(Reservation reservation) {
        reservationService.saveReservation(reservation);
        return "redirect:/reservations";
    }
}
