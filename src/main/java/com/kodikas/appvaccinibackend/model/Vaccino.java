package com.kodikas.appvaccinibackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Vaccino {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idVaccino;
    private String nomeVaccino;
    private Long quantita;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "id_campagna",
            referencedColumnName = "idCampagna"
    )

    private CampagnaVaccinale campagnaVaccinale;
//    private AventiDiritto aventiDiritto;

    public Vaccino(String nomeVaccino, Long quantita) {
        this.nomeVaccino = nomeVaccino;
        this.quantita = quantita;
    }

    public Vaccino(Long idVaccino, String nomeVaccino, Long quantita) {
        this.idVaccino = idVaccino;
        this.nomeVaccino = nomeVaccino;
        this.quantita = quantita;
    }

    @JsonIgnore
    private CampagnaVaccinale getCampagnaVaccinale() {
        return campagnaVaccinale;
    }
}
