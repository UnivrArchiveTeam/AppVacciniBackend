package com.kodikas.appvaccinibackend.controller;

import com.kodikas.appvaccinibackend.model.Availability;
import com.kodikas.appvaccinibackend.service.AvailabilityService;
import com.kodikas.appvaccinibackend.wrapper.AvailabilityWrapper;
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
    private AvailabilityController undetTest;
    private Availability disponibilita;
    private Availability disponibilita2;

    @BeforeEach
    void setUp(){
        undetTest = new AvailabilityController(availabilityService);
        disponibilita = new Availability(
                "Golosine",1L,
                LocalDate.of(2021,05,06),LocalDate.of(2021,05,21),
                LocalTime.of(9,00),LocalTime.of(12,00));
        disponibilita2 = new Availability(
                "SantaLucia",2L,
                LocalDate.of(2021,05,02),LocalDate.of(2021,05,25),
                LocalTime.of(11,00),LocalTime.of(13,00));

    }
    @Test
    void getAvailabilityALL() {
        List<Availability> list_availability = List.of(disponibilita,disponibilita2);

        when(availabilityService.getAllAvailability()).thenReturn(list_availability);

        AvailabilityWrapper result = undetTest.getAvailabilityALL();

        verify(availabilityService).getAllAvailability();

        assertEquals(result.getAvailability(),list_availability);
    }

    @Test
    void registerNewDisponibilita() {

        when(availabilityService.addAvailability(disponibilita)).thenReturn(disponibilita);

        Availability result = undetTest.registerNewAvailability(disponibilita);

        verify(availabilityService).addAvailability(disponibilita);

        assertEquals(disponibilita,result);
    }

    @Test
    void getAvailability() {
        List<Availability>list_availability = List.of(disponibilita,disponibilita2);
        Long id = 2L;
        when(availabilityService.getAvailabilitybyId_Vaccine(id)).thenReturn(List.of(disponibilita2));

        AvailabilityWrapper result =undetTest.getAvailability(id);
        int check = 0;
        for(Availability find : result.getAvailability()){

            if(!(find.getId().getIdVaccine().equals(id))){
                check ++;
            }
        }

        assertEquals(check,0);

    }
}