package com.kodikas.appvaccinibackend.repository;
import com.kodikas.appvaccinibackend.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface ReservationRepository  extends JpaRepository<Reservation,Long>  {

    List<Reservation> findAllByFiscalCode(String fiscalCode);
}
