package com.kodikas.appvaccinibackend.service;
import com.kodikas.appvaccinibackend.model.Entitled;
import com.kodikas.appvaccinibackend.model.Reservation;
import com.kodikas.appvaccinibackend.repository.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReservationService {

    final private ReservationRepository reservationRepository ;

    public List<Reservation> getAllReservations () { return reservationRepository.findAll();}

    public Reservation addReservation (Reservation newEntry){ return reservationRepository.save(newEntry);}

    public List<Reservation> getReservation(String fiscalcode){

        List<Reservation> list_reservations = reservationRepository.findAllByFiscalcode(fiscalcode);

        if(list_reservations.isEmpty()){
            throw new IllegalStateException("I have not found any reservations for this fiscalcode");
        }

        return list_reservations;
    }
}
