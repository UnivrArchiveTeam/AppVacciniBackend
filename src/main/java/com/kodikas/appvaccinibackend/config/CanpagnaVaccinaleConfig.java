package com.kodikas.appvaccinibackend.config;

import com.kodikas.appvaccinibackend.model.CampagnaVaccinale;
import com.kodikas.appvaccinibackend.repository.CampagnaVaccinaleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CanpagnaVaccinaleConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            CampagnaVaccinaleRepository repository) {
        return args -> {
            CampagnaVaccinale campagnaVaccinale1 = new CampagnaVaccinale(
                    "campagna1"
            );
            CampagnaVaccinale campagnaVaccinale2 = new CampagnaVaccinale(
                    "campagna2"
            );

            repository.saveAll(
                    List.of(campagnaVaccinale1, campagnaVaccinale2)
            );
        };
    }
}