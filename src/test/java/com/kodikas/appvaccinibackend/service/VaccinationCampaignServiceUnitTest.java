package com.kodikas.appvaccinibackend.service;

import com.kodikas.appvaccinibackend.model.VaccinationCampaign;
import com.kodikas.appvaccinibackend.model.Vaccine;
import com.kodikas.appvaccinibackend.repository.CampagnaVaccinaleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VaccinationCampaignServiceUnitTest {
    @Mock
    private CampagnaVaccinaleRepository campagnaVaccinaleRepository;
    private CampagnaVaccinaleService underTest;
    private VaccinationCampaign vaccinationCampaign;
    private VaccinationCampaign expectedVaccinationCampaign;

    @BeforeEach
    void setUp() {
        this.underTest = new CampagnaVaccinaleService(campagnaVaccinaleRepository);
        this.vaccinationCampaign = new VaccinationCampaign(
                "campagna2",
                Set.of(
                        new Vaccine(
                                "jansen",
                                100L
                        )
                )
        );
        this.expectedVaccinationCampaign = new VaccinationCampaign(
                2L,
                "campagna2",
                Set.of(
                        new Vaccine(
                                1L,
                                "jansen",
                                100L
                        )
                )
        );
    }

    @AfterEach
    void tearDown() {
        campagnaVaccinaleRepository.deleteAll();
    }

    @Test
    void canGetAllCampagneVaccinali() {
        // given
        List<VaccinationCampaign> campagne = List.of(
                new VaccinationCampaign(
                        "campagna1"
                ),
                expectedVaccinationCampaign
        );

        // when
        when(campagnaVaccinaleRepository.findAll()).thenReturn(
                campagne
        );

        List<VaccinationCampaign> result = underTest.getCampagneVaccinali();

        // then
        verify(campagnaVaccinaleRepository).findAll();
        assertThat(result.get(0).getDiseaseName()).isNotNull();
    }

    @Test
    void shouldAddCampagnaVaccinale() {
        // when
        when(campagnaVaccinaleRepository.save(any())).thenReturn(expectedVaccinationCampaign);

        // then
        VaccinationCampaign result = underTest.addCampagnaVaccinale(vaccinationCampaign);
        verify(campagnaVaccinaleRepository).save(vaccinationCampaign);
        assertThat(result).isEqualTo(expectedVaccinationCampaign);
    }

    @Test
    void shouldNotAddExistingCampagnaVaccinale() {
        // when
        when(campagnaVaccinaleRepository.existsById(expectedVaccinationCampaign.getCampaignID()))
                .thenReturn(true);

        // then
        assertThatThrownBy(
                () -> underTest.addCampagnaVaccinale(expectedVaccinationCampaign)
        ).isInstanceOf(IllegalStateException.class)
                .hasMessage("The given id is already taken");

        verify(campagnaVaccinaleRepository, never()).save(expectedVaccinationCampaign);
    }
}