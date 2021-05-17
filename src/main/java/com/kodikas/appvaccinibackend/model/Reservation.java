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
    String fiscalcode;
    String clinicname;
    Long idVaccine;
    LocalDateTime reservation;

}
