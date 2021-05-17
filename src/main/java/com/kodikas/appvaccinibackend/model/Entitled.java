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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Vaccine vaccine;

    public Entitled(String category) {
        this.category = category;
    }

    public Entitled(String category, Vaccine vaccine) {
        this.category = category;
        this.vaccine = vaccine;
    }
}
