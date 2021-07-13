package com.kodikas.appvaccinibackend.model;

import com.kodikas.appvaccinibackend.id.IdReservation;
import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Reservation {
    @EmbeddedId
    private IdReservation reservationId;
    private String clinicName;
    private LocalDate  date;
    private LocalTime  time;

    public Reservation(Long idVaccine,String fiscalcode , String clinicName, LocalDate date, LocalTime time) {
        this.reservationId = new IdReservation(idVaccine,fiscalcode);
        this.clinicName = clinicName;
        this.date = date;
        this.time = time;
    }
}
