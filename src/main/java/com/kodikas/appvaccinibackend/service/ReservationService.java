package com.kodikas.appvaccinibackend.service;
import com.kodikas.appvaccinibackend.model.Reservation;
import com.kodikas.appvaccinibackend.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository ;

    public List<Reservation> getAllReservations () { return reservationRepository.findAll();}

    public Reservation addReservation (Reservation newEntry){ return reservationRepository.save(newEntry);}

    public List<Reservation> getReservation(String fiscalCode){

        List<Reservation> reservationList = reservationRepository.findAllById(List.of(fiscalCode));

        if(reservationList.isEmpty()){
            throw new IllegalStateException("I have not found any reservations for this fiscalCode");
        }

        return reservationList;
    }
}
