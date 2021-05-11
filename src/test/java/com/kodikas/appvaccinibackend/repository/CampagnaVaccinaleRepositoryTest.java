package com.kodikas.appvaccinibackend.repository;

import com.kodikas.appvaccinibackend.model.CampagnaVaccinale;
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
class CampagnaVaccinaleRepositoryTest {
    @Autowired private CampagnaVaccinaleRepository underTest;
    CampagnaVaccinale campagnaVaccinale;
    String nomeMalattia;

    @Test
    void injectedComponentsAreNotNull(){
        assertThat(underTest).isNotNull();
    }

    @BeforeEach
    void setUp() {
        nomeMalattia = "campagna1";
        campagnaVaccinale = new CampagnaVaccinale(
                nomeMalattia
        );
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShoudCheckCampagnaVaccinaleExistanceByNomeMalattia() {
        underTest.save(campagnaVaccinale);
        boolean result = underTest.existsByNomeMalattia(nomeMalattia);
        assertThat(result).isTrue();
    }

    @Test
    void itShoudRetrieveCampagnaVaccinaleByNomeMalattia() {
        underTest.save(campagnaVaccinale);
        CampagnaVaccinale result = underTest.findCampagnaVaccinaleByNomeMalattia(nomeMalattia).get();
        assertThat(result).isNotNull();
        assertThat(result.getNomeMalattia()).isEqualTo(nomeMalattia);
    }
}