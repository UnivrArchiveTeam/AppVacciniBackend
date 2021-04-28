package com.kodikas.appvaccinibackend.wrapper;

import com.kodikas.appvaccinibackend.model.CampagnaVaccinale;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class CampagnaVaccinaleWrapper {
    List<CampagnaVaccinale> campagneVaccinali;
}