package com.kodikas.appvaccinibackend.service;

import com.kodikas.appvaccinibackend.model.Availability;
import com.kodikas.appvaccinibackend.repository.AvailabilityRepostitory;
import com.kodikas.appvaccinibackend.wrapper.AvailabilityWrapper;
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
                "Golosine",1L,"over80",
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
    void getByDateandCategoryAvailability() {

        //when
        List<Availability> availabilityList = List.of(entry);
        String category = "over80";
        LocalDate date = LocalDate.of(2021 ,05 ,13);

        when(availabilityRepostitory.findAllByCategoria(category)).thenReturn(availabilityList);


        List<Availability> result = underTest.getByDateandCategoryAvailability(category,date);


        verify(availabilityRepostitory).findAllByCategoria(category);
        boolean check = false;

        for (Availability find : result){
            if(find.getCategoria().equals(category)){
                check = true ;
                break;
            }
        }

        assertThat(check).isTrue();

    }
    @Test
    void getAvailabilitybyCategoryAndClinic(){
        //when
        List<Availability> availabilityList = List.of(entry);
        String category = "over80";
        String clinic = "Golosine";


        when(availabilityRepostitory.findAllById_NomeAmbulatorioAndCategoria(clinic,category)).thenReturn(availabilityList);

        List<Availability> result = underTest.getAvailabilitybyCategoryAndClinic(category,clinic);

        boolean check = false;
        for (Availability find : result){
            if (find.getCategoria().equals(category)&&find.getId().getNomeAmbulatorio().equals(clinic)){
                check = true;
            }
        }
        assertThat(check).isTrue();
    }
}