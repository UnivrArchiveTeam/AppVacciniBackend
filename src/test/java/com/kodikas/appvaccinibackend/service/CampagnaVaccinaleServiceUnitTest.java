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

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CampagnaVaccinaleServiceUnitTest {
    @Mock
    private CampagnaVaccinaleRepository campagnaVaccinaleRepository;
    private CampagnaVaccinaleService underTest;
    private CampagnaVaccinale campagnaVaccinale;

    @BeforeEach
    void setUp() {
        this.underTest = new CampagnaVaccinaleService(campagnaVaccinaleRepository);
        this.campagnaVaccinale = new CampagnaVaccinale(
                2L,
                "campagna2",
                Set.of(
                        new Vaccino(
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
                campagnaVaccinale
        );

        // when
        when(campagnaVaccinaleRepository.findAll()).thenReturn(
                campagne
        );

        underTest.getCampagneVaccinali();

        // then
        verify(campagnaVaccinaleRepository).findAll();
    }

    @Test
    void shouldAddCampagnaVaccinale() {
        // when
        underTest.addCampagnaVaccinale(campagnaVaccinale);

        // then
        verify(campagnaVaccinaleRepository).save(campagnaVaccinale);
    }

    @Test
    void shouldNotAddExistingCampagnaVaccinale() {
        // when
        when(campagnaVaccinaleRepository.existsById(campagnaVaccinale.getIdCampagna()))
                .thenReturn(true);

        // then
        assertThatThrownBy(
                () -> underTest.addCampagnaVaccinale(campagnaVaccinale)
        ).isInstanceOf(IllegalStateException.class)
                .hasMessage("The given id is already taken");

        verify(campagnaVaccinaleRepository, never()).save(campagnaVaccinale);
    }
}