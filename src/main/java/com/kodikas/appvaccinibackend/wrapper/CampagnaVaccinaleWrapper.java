package com.kodikas.appvaccinibackend.wrapper;

import com.kodikas.appvaccinibackend.model.CampagnaVaccinale;

import java.util.List;

public class CampagnaVaccinaleWrapper {
    List<CampagnaVaccinale> campagneVaccinali;

    public CampagnaVaccinaleWrapper(List<CampagnaVaccinale> campagneVaccinali) {
        this.campagneVaccinali = campagneVaccinali;
    }

    public List<CampagnaVaccinale> getCampagneVaccinali() {
        return campagneVaccinali;
    }

    public void setCampagneVaccinali(List<CampagnaVaccinale> campagneVaccinali) {
        this.campagneVaccinali = campagneVaccinali;
    }
}