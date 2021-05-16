package com.kodikas.appvaccinibackend.config;


import com.kodikas.appvaccinibackend.model.Availability;
import com.kodikas.appvaccinibackend.repository.AvailabilityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Configuration
public class AvailabilityConfig {

    @Bean
    CommandLineRunner commandLineRunnerAvailability(AvailabilityRepository repository){
        return  args -> {
            Availability golosine = new Availability(
                   "Golosine",
                    2341244L,
                    LocalDate.of(2021,05,03),
                    LocalDate.of(2021,05,05),
                    LocalTime.of(13,00),
                    LocalTime.of(15,00)
            );
            Availability santa = new Availability(
                    "Santa",
                    2341344L,
                    LocalDate.of(2021,05,02),
                    LocalDate.of(2021,05,06),
                    LocalTime.of(13,00),
                    LocalTime.of(15,00)
            );
            repository.saveAll(List.of(golosine,santa));
        };
    }
}
