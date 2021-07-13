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
    String diseaseName;

    @Test
    void injectedComponentsAreNotNull(){
        assertThat(underTest).isNotNull();
    }

    @BeforeEach
    void setUp() {
        diseaseName = "disease_example";
        vaccinationCampaign = new VaccinationCampaign(
                diseaseName
        );
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void existsByDiseaseName_shouldReturnTrue() {
        underTest.save(vaccinationCampaign);
        boolean result = underTest.existsByDiseaseName(diseaseName);
        assertThat(result).isTrue();
    }

    @Test
    void existsByDiseaseName_shouldReturnFalse() {
        boolean result = underTest.existsByDiseaseName(diseaseName);
        assertThat(result).isFalse();
    }

    @Test
    void findVaccinationCampaignByDiseaseName_shouldRetrieveVaccinationCampaignFromDatabase() {
        underTest.save(vaccinationCampaign);
        VaccinationCampaign result = underTest.findVaccinationCampaignByDiseaseName(diseaseName).get();
        assertThat(result).isNotNull();
        assertThat(result.getDiseaseName()).isEqualTo(diseaseName);
    }
}