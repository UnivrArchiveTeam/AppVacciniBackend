package com.kodikas.appvaccinibackend.service;

import com.kodikas.appvaccinibackend.model.CampagnaVaccinale;
import com.kodikas.appvaccinibackend.model.Vaccino;
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
class CampagnaVaccinaleServiceUnitTest {
    @Mock
    private CampagnaVaccinaleRepository campagnaVaccinaleRepository;
    private CampagnaVaccinaleService underTest;
    private CampagnaVaccinale campagnaVaccinale;
    private CampagnaVaccinale expectedCampagnaVaccinale;

    @BeforeEach
    void setUp() {
        this.underTest = new CampagnaVaccinaleService(campagnaVaccinaleRepository);
        this.campagnaVaccinale = new CampagnaVaccinale(
                "campagna2",
                Set.of(
                        new Vaccino(
                                "jansen",
                                100L
                        )
                )
        );
        this.expectedCampagnaVaccinale = new CampagnaVaccinale(
                2L,
                "campagna2",
                Set.of(
                        new Vaccino(
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
        List<CampagnaVaccinale> campagne = List.of(
                new CampagnaVaccinale(
                        "campagna1"
                ),
                expectedCampagnaVaccinale
        );

        // when
        when(campagnaVaccinaleRepository.findAll()).thenReturn(
                campagne
        );

        List<CampagnaVaccinale> result = underTest.getCampagneVaccinali();

        // then
        verify(campagnaVaccinaleRepository).findAll();
        assertThat(result.get(0).getNomeMalattia()).isNotNull();
    }

    @Test
    void shouldAddCampagnaVaccinale() {
        // when
        when(campagnaVaccinaleRepository.save(any())).thenReturn(expectedCampagnaVaccinale);

        // then
        CampagnaVaccinale result = underTest.addCampagnaVaccinale(campagnaVaccinale);
        verify(campagnaVaccinaleRepository).save(campagnaVaccinale);
        assertThat(result).isEqualTo(expectedCampagnaVaccinale);
    }

    @Test
    void shouldNotAddExistingCampagnaVaccinale() {
        // when
        when(campagnaVaccinaleRepository.existsById(expectedCampagnaVaccinale.getIdCampagna()))
                .thenReturn(true);

        // then
        assertThatThrownBy(
                () -> underTest.addCampagnaVaccinale(expectedCampagnaVaccinale)
        ).isInstanceOf(IllegalStateException.class)
                .hasMessage("The given id is already taken");

        verify(campagnaVaccinaleRepository, never()).save(expectedCampagnaVaccinale );
    }
}