package com.kodikas.appvaccinibackend.service;

import com.kodikas.appvaccinibackend.model.CampagnaVaccinale;
import com.kodikas.appvaccinibackend.repository.CampagnaVaccinaleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CampagnaVaccinaleServiceUnitTest {
    @Mock
    private CampagnaVaccinaleRepository campagnaVaccinaleRepository;
    private CampagnaVaccinaleService underTest;

    @BeforeEach
    void setUp() {
        this.underTest = new CampagnaVaccinaleService(campagnaVaccinaleRepository);
    }

    @Test
    void canGetAllCampagneVaccinali() {
        // given
        List<CampagnaVaccinale> campagne = List.of(
                new CampagnaVaccinale(
                        "campagna1"
                ),
                new CampagnaVaccinale(
                        "campagna2"
                )
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
        // given
        CampagnaVaccinale campagnaVaccinale = new CampagnaVaccinale(
                "campagna1"
        );

        // when
        underTest.addCampagnaVaccinale(campagnaVaccinale);

        // then
        verify(campagnaVaccinaleRepository).save(campagnaVaccinale);
    }

    @Test
    void shouldNotAddExistingCampagnaVaccinale() {
        // given
        CampagnaVaccinale campagnaVaccinale = new CampagnaVaccinale(
                1L,
                "campagna1"
        );


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