package com.kodikas.appvaccinibackend.model;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.*;


@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Reservation {

    @Id
    String fiscalCode;
    String clinicName;
    Long idVaccine;
    LocalDateTime reservationDateTime;

}
