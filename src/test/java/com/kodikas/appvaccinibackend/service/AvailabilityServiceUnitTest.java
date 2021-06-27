package com.kodikas.appvaccinibackend.service;

import com.kodikas.appvaccinibackend.model.Availability;
import com.kodikas.appvaccinibackend.repository.AvailabilityRepository;
import com.kodikas.appvaccinibackend.repository.VaccineRepository;
import com.kodikas.appvaccinibackend.wrapper.VaccineIdWrapper;
import com.kodikas.appvaccinibackend.wrapper.VaccineWrapper;
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
    @Mock
    private VaccineRepository vaccineRepository;
    private AvailabilityService underTest;
    private Availability entry;

    @BeforeEach
    void setUp(){
        underTest= new AvailabilityService(availabilityRepository, vaccineRepository);
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
         VaccineIdWrapper vaccineWrapper = new VaccineIdWrapper(List.of(idVaccine));
        when(availabilityRepository.findAllByAvailabilityId_IdVaccine(idVaccine)).thenReturn(list_availability);

        List<Availability>result = underTest.getAvailabilityByIdVaccine(vaccineWrapper);
        boolean check = false;

        for (Availability find : result){
            if (find.getAvailabilityId().getIdVaccine() == idVaccine) {
                check = true;
                break;
            }
        }

        assertThat(check).isTrue();
    }

    @Test

    void getAvailabilitybyId_Vaccine_shouldThrowErrorWhenIdDoesNotCorrect(){
        long id = -123L;
        VaccineIdWrapper vaccineWrapper = new VaccineIdWrapper(List.of(id));
        assertThatThrownBy(
                ()-> underTest.getAvailabilityByIdVaccine(vaccineWrapper)
        ).isInstanceOf(IllegalStateException.class).hasMessage("Invalid vaccine availabilityId");

        verify(availabilityRepository,never()).findAllByAvailabilityId_IdVaccine(any());
    }

    @Test
    void getAvailabilitybyId_VaccineshouldThrowErrorWhenfindsnothing_(){
        long id = 232L;
        List<Long> test = List.of(id);
        VaccineIdWrapper vaccineIdWrapper = new VaccineIdWrapper(test);
        assertThatThrownBy(
                ()-> underTest.getAvailabilityByIdVaccine(vaccineIdWrapper)
        ).isInstanceOf(IllegalStateException.class).hasMessage("No availability found matching the vaccine availabilityId");

        verify(availabilityRepository).findAllByAvailabilityId_IdVaccine(any());
    }
}