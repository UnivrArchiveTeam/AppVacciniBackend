package com.kodikas.appvaccinibackend.wrapper;

import com.kodikas.appvaccinibackend.model.VaccinationCampaign;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CampagnaVaccinaleWrapper {
    List<VaccinationCampaign> campagneVaccinali;
}