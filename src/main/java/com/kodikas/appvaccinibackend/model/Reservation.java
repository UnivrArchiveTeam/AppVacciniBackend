package com.kodikas.appvaccinibackend.model;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    private String fiscalCode;
    private String clinicName;
    private Long idVaccine;
    private LocalDate  date;
    private LocalTime  time;
}
