package com.kodikas.appvaccinibackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Vaccine {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long vaccineID;
    private String vaccineName;
    private Long quantity;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn
    private VaccinationCampaign vaccinationCampaign;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    private Set<Availability> availabilities = new HashSet<>();

    @OneToMany(mappedBy = "vaccine", cascade = CascadeType.ALL)
    private Set<Entitled> entitleds = new HashSet<>();

    public Vaccine(String vaccineName, long quantity) {
        this.vaccineName = vaccineName;
        this.quantity = quantity;
    }

    public Vaccine(long vaccineID, String vaccineName, long quantity) {
        this.vaccineID = vaccineID;
        this.vaccineName = vaccineName;
        this.quantity = quantity;
    }

    public Vaccine(String vaccineName, Long quantity, VaccinationCampaign vaccinationCampaign) {
        this.vaccineName = vaccineName;
        this.quantity = quantity;
        this.vaccinationCampaign = vaccinationCampaign;
    }

    public Vaccine(long vaccineID, String vaccineName, Long quantity, VaccinationCampaign vaccinationCampaign) {
        this.vaccineID = vaccineID;
        this.vaccineName = vaccineName;
        this.quantity = quantity;
        this.vaccinationCampaign = vaccinationCampaign;
    }

    @JsonIgnore
    public VaccinationCampaign getVaccinationCampaign() {
        return vaccinationCampaign;
    }

    @JsonIgnore
    public Set<Availability> getAvailabilities() {
        return availabilities;
    }

    @JsonIgnore
    public Set<Entitled> getEntitleds() {
        return entitleds;
    }
}