package com.kodikas.appvaccinibackend.controller;

import com.kodikas.appvaccinibackend.model.Availability;
import com.kodikas.appvaccinibackend.service.AvailabilityService;
import com.kodikas.appvaccinibackend.wrapper.AvailabilityWrapper;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AvailabilityControllerUnitTest {

    @Mock
    private AvailabilityService availabilityService;
    private AvailabilityController underTest;
    private Availability availability;
    private Availability availability2;

    @BeforeEach
    void setUp(){
        underTest = new AvailabilityController(availabilityService);
        availability = new Availability(
                "Golosine",1L,
                LocalDate.of(2021,05,06),LocalDate.of(2021,05,21),
                LocalTime.of(9,00),LocalTime.of(12,00));
        availability2 = new Availability(
                "SantaLucia",2L,
                LocalDate.of(2021,05,02),LocalDate.of(2021,05,25),
                LocalTime.of(11,00),LocalTime.of(13,00));

    }
    @Test
    void getAvailabilityALL() {
        List<Availability> list_availability = List.of(availability, availability2);

        when(availabilityService.getAllAvailability()).thenReturn(list_availability);

        AvailabilityWrapper result = underTest.getAvailabilityALL();

        verify(availabilityService).getAllAvailability();

        assertEquals(result.getAvailability(),list_availability);
    }

    @Test
    void registerNewAvailability() {

        when(availabilityService.addAvailability(availability)).thenReturn(availability);

        Availability result = underTest.registerNewAvailability(availability);

        verify(availabilityService).addAvailability(availability);

        assertEquals(availability,result);
    }

    @Test
    void getAvailability() {
//        List<Availability>list_availability = List.of(availability, availability2);
//        long id = 2L;
//        VaccineIdWrapper vaccineWrapper = new VaccineIdWrapper(List.of(id)) ;
//        when(availabilityService.getAvailabilityByIdVaccine(vaccineWrapper)).thenReturn(List.of(availability2));
//
//        AvailabilityWrapper result = underTest.getAvailability(vaccineWrapper);
//        int check = 0;
//        for(Availability find : result.getAvailability()){
//
//            assert vaccineWrapper.getIdVaccines() != null;
//            for (Long idV : vaccineWrapper.getIdVaccines())
//            if(!(find.getAvailabilityId().getIdVaccine().equals(idV))){
//                check ++;
//            }
//        }
//
//        assertEquals(check,0);
//
    }
}