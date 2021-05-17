package com.kodikas.appvaccinibackend.controller;

import com.kodikas.appvaccinibackend.model.Reservation;
import com.kodikas.appvaccinibackend.service.ReservationService;
import com.kodikas.appvaccinibackend.wrapper.ReservationWrapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public Reservation addEntitled(Reservation newEntry){ return reservationService.addReservation(newEntry);}

    @GetMapping("/{fiscalCode}")
    public  ReservationWrapper getReservationsByFiscalcode(@PathVariable String fiscalCode){
        return new ReservationWrapper(reservationService.getReservation(fiscalCode));
    }
}
