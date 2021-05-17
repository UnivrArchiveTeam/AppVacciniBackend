package com.kodikas.appvaccinibackend.model;

import lombok.*;

import java.time.LocalDate;
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
    LocalDate reservation;

}
