package com.kodikas.appvaccinibackend.service;

import com.kodikas.appvaccinibackend.model.Reservation;
import com.kodikas.appvaccinibackend.model.Vaccine;
import com.kodikas.appvaccinibackend.repository.ReservationRepository;
import com.kodikas.appvaccinibackend.wrapper.VaccineIdWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final VaccineService vaccineService;

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation addReservation(Reservation newEntry) {
        vaccineService.decreaseQuantity(newEntry.getReservationId().getIdVaccine());
        return reservationRepository.save(newEntry);
    }


    public List<Reservation> getReservation(String fiscalCode) {

        List<Reservation> reservationList = reservationRepository.findAllById(List.of(fiscalCode));

        if (reservationList.isEmpty()) {
            throw new IllegalStateException("I have not found any reservations for this fiscalCode");
        }

        return reservationList;
    }

    public List<Reservation> getReservationbyDate(String clinicName, Long idVaccine, LocalDate date) {

        List<Reservation> reservationList = null;

        reservationList = reservationRepository.findAllByClinicNameAndReservationId_IdVaccineAndAndDate(clinicName, idVaccine, date);

        if (reservationList.isEmpty()) {
            throw new IllegalStateException("I have not found any reservations in date");
        }

        return reservationList;
    }

    public List<Reservation> getReservationbyFiscalCode(String fiscalcode, VaccineIdWrapper idVaccines) {

        List<Reservation> reservationList = null;

        for (long idVaccine : idVaccines.getIdVaccines()) {

            if (reservationList.isEmpty()) {
                reservationList = reservationRepository.findAllByReservationId_FiscalCodeAndReservationId_IdVaccine(fiscalcode, idVaccine);
            } else {
                reservationList.addAll(reservationRepository.findAllByReservationId_FiscalCodeAndReservationId_IdVaccine(fiscalcode, idVaccine));
            }
        }
        if (reservationList.isEmpty()) {
            throw new IllegalStateException("I have not found any reservations by Fiscalcode");
        }

        return reservationList;
    }
}
