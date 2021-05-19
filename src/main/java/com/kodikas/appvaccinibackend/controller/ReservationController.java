package com.kodikas.appvaccinibackend.controller;

import com.kodikas.appvaccinibackend.model.Reservation;
import com.kodikas.appvaccinibackend.service.ReservationService;
import com.kodikas.appvaccinibackend.wrapper.ReservationWrapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/reservations")
public class ReservationController {

    private final ReservationService reservationService ;

    @GetMapping
    public ReservationWrapper getAllReservations (){
        return new ReservationWrapper(reservationService.getAllReservations());
    }

    @PostMapping
    public Reservation addReservation(@RequestBody Reservation reservation){
        return reservationService.addReservation(reservation);
    }

    @GetMapping("/{fiscalCode}")
    public ReservationWrapper getReservationsByFiscalCode(@PathVariable String fiscalCode){
        return new ReservationWrapper(reservationService.getReservation(fiscalCode));
    }
}