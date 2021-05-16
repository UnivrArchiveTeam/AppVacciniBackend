package com.kodikas.appvaccinibackend.model;

import lombok.*;

import javax.persistence.*;


@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Entitled {
    @Id
    Long idEntilted;
    String category;
    Long idVaccine;
}
