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
            @GeneratedValue(strategy=GenerationType.AUTO)
    Long idEntilted;
    String Category;
    Long idVaccine;
}
