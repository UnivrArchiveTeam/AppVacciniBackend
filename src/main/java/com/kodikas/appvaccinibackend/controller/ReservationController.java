package com.kodikas.appvaccinibackend.controller;

import com.kodikas.appvaccinibackend.model.Reservation;
import com.kodikas.appvaccinibackend.service.ReservationService;
import com.kodikas.appvaccinibackend.wrapper.ReservationWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "vaccine/Reservations")
public class ReservationController {

    private ReservationService reservationService ;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public ReservationWrapper getAllReservations (){return new ReservationWrapper(reservationService.getAllReservations());}

    @GetMapping
    public Reservation addEntitled(Reservation newEntry){ return reservationService.addReservation(newEntry);}

    @GetMapping("/Reservations/{fiscalcode}")
    public  ReservationWrapper getReservationsbyfiscalcode (@PathVariable String fiscalcode){
        return new ReservationWrapper(reservationService.getReservation(fiscalcode));
    }
}
