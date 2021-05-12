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
public class Vaccine {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long vaccineID;
    private String vaccineName;
    private Long quantity;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "campaign_id",
            referencedColumnName = "campaignID"
    )
    private VaccinationCampaign vaccinationCampaign;
//    private AventiDiritto aventiDiritto;

    public Vaccine(String vaccineName, Long quantity) {
        this.vaccineName = vaccineName;
        this.quantity = quantity;
    }

    public Vaccine(Long vaccineID, String vaccineName, Long quantity) {
        this.vaccineID = vaccineID;
        this.vaccineName = vaccineName;
        this.quantity = quantity;
    }

    @JsonIgnore
    private VaccinationCampaign getVaccinationCampaign() {
        return vaccinationCampaign;
    }
}
