package com.kodikas.appvaccinibackend.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampagnaVaccinale {
    @Id
    @GeneratedValue
    private Long idCampagna;
    private String nomeCampagna;
//    Vaccino vaccino;

    public CampagnaVaccinale(String nomeCampagna) {
        this.nomeCampagna = nomeCampagna;
    }
}
