package com.kodikas.appvaccinibackend.service;

import com.kodikas.appvaccinibackend.model.Availability;
import com.kodikas.appvaccinibackend.repository.AvailabilityRepostitory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class AvailabilityServiceUnitTest {

    @Mock
    private AvailabilityRepostitory availabilityRepostitory;
    private AvailabilityService underTest;
    private Availability entry;

    @BeforeEach
    void setUp(){
        underTest= new AvailabilityService(availabilityRepostitory);
        entry= new Availability(
                "Golosine",1L,
                LocalDate.of(2021,05,06),LocalDate.of(2021,05,21),
                LocalTime.of(9,00),LocalTime.of(12,00));
    }

    @Test
    void getAllAvailability() {
        List<Availability> availabilityList = List.of(entry);

        when(availabilityRepostitory.findAll()).thenReturn(availabilityList);

        underTest.getAllAvailability();

        verify(availabilityRepostitory).findAll();
    }

    @Test
    void addAvailability() {
        underTest.addAvailability(entry);

        verify(availabilityRepostitory).save(entry);
    }

    @Test
    void getAvailabilitybyId_Vaccine_shouldcorrectlyreturns(){
        List<Availability> list_availability = List.of(entry);
         Long idVaccine = 1L ;

        when(availabilityRepostitory.findAllById_IdVaccino(idVaccine)).thenReturn(list_availability);

        List<Availability>result = underTest.getAvailabilitybyId_Vaccine(idVaccine);
        boolean check = false;

        for (Availability find : result){
            if(find.getId().getIdVaccine().equals(idVaccine)){
                check = true;
            }
        }

        assertThat(check).isTrue();
    }

    @Test

    void getAvailabilitybyId_Vaccine_shouldThrowErrorWhenIdDoesNotCorrect(){
        long id = -123L;
        assertThatThrownBy(
                ()-> underTest.getAvailabilitybyId_Vaccine(id)
        ).isInstanceOf(IllegalStateException.class).hasMessage("Invalid vaccine id");

        verify(availabilityRepostitory,never()).findAllById_IdVaccino(any());
    }

    @Test
    void getAvailabilitybyId_VaccineshouldThrowErrorWhenfindsnothing_(){
        long id = 232L;
        assertThatThrownBy(
                ()-> underTest.getAvailabilitybyId_Vaccine(id)
        ).isInstanceOf(IllegalStateException.class).hasMessage("No availability found matching the vaccine id");

        verify(availabilityRepostitory).findAllById_IdVaccino(any());
    }
}