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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long entitledId;
    private String category;
    private Long idVaccine;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Vaccine vaccine;

    public Entitled(String category, Long idVaccine) {

        this.category = category;
        this.idVaccine = idVaccine;
    }

    public Entitled(String category, Long idVaccine, Vaccine vaccine) {
        this.category = category;
        this.idVaccine = idVaccine;
        this.vaccine = vaccine;
    }
}
