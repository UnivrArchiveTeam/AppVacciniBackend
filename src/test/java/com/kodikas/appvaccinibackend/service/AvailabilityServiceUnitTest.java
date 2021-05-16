package com.kodikas.appvaccinibackend.service;

import com.kodikas.appvaccinibackend.model.Availability;
import com.kodikas.appvaccinibackend.repository.AvailabilityRepository;
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
    private AvailabilityRepository availabilityRepository;
    private AvailabilityService underTest;
    private Availability entry;

    @BeforeEach
    void setUp(){
        underTest= new AvailabilityService(availabilityRepository);
        entry= new Availability(
                "Golosine",1L,
                LocalDate.of(2021,05,06),LocalDate.of(2021,05,21),
                LocalTime.of(9,00),LocalTime.of(12,00));
    }

    @Test
    void getAllAvailability() {
        List<Availability> availabilityList = List.of(entry);

        when(availabilityRepository.findAll()).thenReturn(availabilityList);

        underTest.getAllAvailability();

        verify(availabilityRepository).findAll();
    }

    @Test
    void addAvailability() {
        underTest.addAvailability(entry);

        verify(availabilityRepository).save(entry);
    }

    @Test
    void getAvailabilitybyId_Vaccine_shouldcorrectlyreturns(){
        List<Availability> list_availability = List.of(entry);
         Long idVaccine = 1L ;

        when(availabilityRepository.findAllById_IdVaccine(idVaccine)).thenReturn(list_availability);

        List<Availability>result = underTest.getAvailabilityByIdVaccine(idVaccine);
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
                ()-> underTest.getAvailabilityByIdVaccine(id)
        ).isInstanceOf(IllegalStateException.class).hasMessage("Invalid vaccine id");

        verify(availabilityRepository,never()).findAllById_IdVaccine(any());
    }

    @Test
    void getAvailabilitybyId_VaccineshouldThrowErrorWhenfindsnothing_(){
        long id = 232L;
        assertThatThrownBy(
                ()-> underTest.getAvailabilityByIdVaccine(id)
        ).isInstanceOf(IllegalStateException.class).hasMessage("No availability found matching the vaccine id");

        verify(availabilityRepository).findAllById_IdVaccine(any());
    }
}