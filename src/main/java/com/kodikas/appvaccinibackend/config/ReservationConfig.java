package com.kodikas.appvaccinibackend.config;

import com.kodikas.appvaccinibackend.model.Reservation;
import com.kodikas.appvaccinibackend.repository.AvailabilityRepository;
import com.kodikas.appvaccinibackend.repository.ReservationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Configuration
public class ReservationConfig {

    @Bean
    CommandLineRunner commandLineRunnerReservation(ReservationRepository repository) {
        return args -> {
            Reservation entry1 = new Reservation(2L,"GRRDFN68H68L414I","Fiera",
                    LocalDate.of(2021,06,28), LocalTime.of(13,0));

            Reservation entry2 = new Reservation(1L,"FRRFTH32C49L058J","Bussolengo\nVia Strada San Vittore - Bussolengo - presso Bocciodromo",
                    LocalDate.of(2021,06,28), LocalTime.of(13,0));

            repository.saveAll(List.of(entry1,entry2));
        };

    }
}
