package com.kodikas.appvaccinibackend.repository;
import com.kodikas.appvaccinibackend.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository  extends JpaRepository<Reservation,String>  {

    List<Reservation> findAllByDate(LocalDate Date);
}
