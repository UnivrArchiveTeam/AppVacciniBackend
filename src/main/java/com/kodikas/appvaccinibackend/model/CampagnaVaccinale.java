package com.kodikas.appvaccinibackend.model;


import lombok.*;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CampagnaVaccinale {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idCampagna;
    private String nomeMalattia;
    @OneToMany(mappedBy = "campagnaVaccinale", cascade = CascadeType.ALL)
    private Set<Vaccino> vaccini;

    public CampagnaVaccinale(String nomeMalattia) {
        this.nomeMalattia = nomeMalattia;
    }

    public CampagnaVaccinale(String nomeMalattia, Set<Vaccino> vaccini) {
        this.nomeMalattia = nomeMalattia;
        this.vaccini = vaccini;
    }
}
