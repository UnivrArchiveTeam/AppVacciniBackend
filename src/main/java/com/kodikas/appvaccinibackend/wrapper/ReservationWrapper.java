package com.kodikas.appvaccinibackend.wrapper;
import com.kodikas.appvaccinibackend.model.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class ReservationWrapper {
    List<Reservation> reservations;
}
