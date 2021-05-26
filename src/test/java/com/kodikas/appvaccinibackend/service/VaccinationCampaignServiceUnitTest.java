package com.kodikas.appvaccinibackend.service;

import com.kodikas.appvaccinibackend.model.VaccinationCampaign;
import com.kodikas.appvaccinibackend.model.Vaccine;
import com.kodikas.appvaccinibackend.repository.VaccinationCampaignRepository;
import com.kodikas.appvaccinibackend.repository.VaccineRepository;
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
    private VaccinationCampaignRepository vaccinationCampaignRepository;
    @Mock
    private VaccineRepository vaccineRepository;
    private VaccinationCampaignService underTest;
    private VaccinationCampaign vaccinationCampaign;
    private VaccinationCampaign expectedVaccinationCampaign;

    @BeforeEach
    void setUp() {
        this.underTest = new VaccinationCampaignService(vaccinationCampaignRepository, vaccineRepository);
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
        vaccinationCampaignRepository.deleteAll();
    }

    @Test
    void getVaccinationCampaigns_shouldReturnListOfVaccinationCampaigns() {
        // given
        List<VaccinationCampaign> campaigns = List.of(
                new VaccinationCampaign(
                        "campagna1"
                ),
                expectedVaccinationCampaign
        );

        // when
        when(vaccinationCampaignRepository.findAll()).thenReturn(
                campaigns
        );

        List<VaccinationCampaign> result = underTest.getVaccinationCampaigns();

        // then
        verify(vaccinationCampaignRepository).findAll();
        assertThat(result.get(0).getDiseaseName()).isNotNull();
    }

    @Test
    void addVaccinationCampaign_shouldReturnVaccinationCampaign() {
        // when
        when(vaccinationCampaignRepository.save(any())).thenReturn(expectedVaccinationCampaign);

        // then
        VaccinationCampaign result = underTest.addVaccinationCampaign(vaccinationCampaign);
        verify(vaccinationCampaignRepository, times(2)).save(vaccinationCampaign);
    }

    @Test
    void addVaccinationCampaign_existingId_shouldThrowException() {
        // when
        when(vaccinationCampaignRepository.existsById(expectedVaccinationCampaign.getCampaignID()))
                .thenReturn(true);

        // then
        assertThatThrownBy(
                () -> underTest.addVaccinationCampaign(expectedVaccinationCampaign)
        ).isInstanceOf(IllegalStateException.class)
                .hasMessage("The given id is already taken");

        verify(vaccinationCampaignRepository, never()).save(expectedVaccinationCampaign);
    }

    @Test
    void addVaccinationCampaign_existingDisease_shouldThrowException() {
        // when
        when(vaccinationCampaignRepository.existsByDiseaseName(expectedVaccinationCampaign.getDiseaseName()))
                .thenReturn(true);

        // then
        assertThatThrownBy(
                () -> underTest.addVaccinationCampaign(expectedVaccinationCampaign)
        ).isInstanceOf(IllegalStateException.class)
                .hasMessage("The given diseaseName is already present");

        verify(vaccinationCampaignRepository, never()).save(expectedVaccinationCampaign);
    }

    @Test
    void addVaccinationCampaign_nullDisease_shouldThrowException() {
        // given
        expectedVaccinationCampaign.setDiseaseName(null);

        // when
        when(vaccinationCampaignRepository.save(any())).thenReturn(expectedVaccinationCampaign);

        // then
        VaccinationCampaign result = underTest.addVaccinationCampaign(vaccinationCampaign);
        verify(vaccinationCampaignRepository, times(2)).save(vaccinationCampaign);
    }
}