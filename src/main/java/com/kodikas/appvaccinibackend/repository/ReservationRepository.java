package com.kodikas.appvaccinibackend.repository;

import com.kodikas.appvaccinibackend.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, String> {

	// findAll by clinicName, idVaccine and date
	List<Reservation> findAllByClinicNameAndReservationId_IdVaccineAndAndDate(String clinicName, Long idVaccine, LocalDate date);

	// findAll by fiscalcode & idvaccine
	List<Reservation> findAllByReservationId_FiscalCodeAndReservationId_IdVaccine(String fiscalCode, Long idVaccine);

	// findAll by fiscalcode
	List<Reservation> findAllByReservationId_FiscalCode(String fiscalCode);
}
