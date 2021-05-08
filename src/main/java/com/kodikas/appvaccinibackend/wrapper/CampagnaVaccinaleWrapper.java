package com.kodikas.appvaccinibackend.wrapper;

import com.kodikas.appvaccinibackend.model.CampagnaVaccinale;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class CampagnaVaccinaleWrapper {
    List<CampagnaVaccinale> campagneVaccinali;
}