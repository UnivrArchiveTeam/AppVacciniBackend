package com.kodikas.appvaccinibackend.wrapper;

import com.kodikas.appvaccinibackend.model.VaccinationCampaign;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VaccinationCampaignWrapper {
    List<VaccinationCampaign> vaccinationCampaigns;
}