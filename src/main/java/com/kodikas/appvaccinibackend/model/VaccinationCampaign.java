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
public class VaccinationCampaign {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long campaignID;
    private String diseaseName;
    @OneToMany(mappedBy = "vaccinationCampaign", cascade = CascadeType.ALL)
    private Set<Vaccine> vaccines;

    public VaccinationCampaign(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public VaccinationCampaign(String diseaseName, Set<Vaccine> vaccines) {
        this.diseaseName = diseaseName;
        this.vaccines = vaccines;
    }
}
