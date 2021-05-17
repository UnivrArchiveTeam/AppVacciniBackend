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
<<<<<<< HEAD
            @GeneratedValue(strategy=GenerationType.AUTO)
    Long idEntilted;
    String Category;
    Long idVaccine;
}
=======
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long entitledId;
    private String category;
    private Long idVaccine;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Vaccine vaccine;

    public Entitled(Long entitledId, String category, Long idVaccine) {
        this.entitledId = entitledId;
        this.category = category;
        this.idVaccine = idVaccine;
    }

    public Entitled(Long entitledId, String category, Long idVaccine, Vaccine vaccine) {
        this.entitledId = entitledId;
        this.category = category;
        this.idVaccine = idVaccine;
        this.vaccine = vaccine;
    }
}
>>>>>>> development
