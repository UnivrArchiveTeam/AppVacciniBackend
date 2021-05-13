package com.kodikas.appvaccinibackend.repository;

import com.kodikas.appvaccinibackend.model.VaccinationCampaign;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class VaccinationCampaignRepositoryTest {
    @Autowired private VaccinationCampaignRepository underTest;
    VaccinationCampaign vaccinationCampaign;
    String nomeMalattia;

    @Test
    void injectedComponentsAreNotNull(){
        assertThat(underTest).isNotNull();
    }

    @BeforeEach
    void setUp() {
        nomeMalattia = "campagna1";
        vaccinationCampaign = new VaccinationCampaign(
                nomeMalattia
        );
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShoudCheckCampagnaVaccinaleExistanceByNomeMalattia() {
        underTest.save(vaccinationCampaign);
        boolean result = underTest.existsByDiseaseName(nomeMalattia);
        assertThat(result).isTrue();
    }

    @Test
    void itShoudRetrieveCampagnaVaccinaleByNomeMalattia() {
        underTest.save(vaccinationCampaign);
        VaccinationCampaign result = underTest.findVaccinationCampaignByDiseaseName(nomeMalattia).get();
        assertThat(result).isNotNull();
        assertThat(result.getDiseaseName()).isEqualTo(nomeMalattia);
    }
}