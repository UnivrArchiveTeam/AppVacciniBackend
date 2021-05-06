package com.kodikas.appvaccinibackend.config;

import com.kodikas.appvaccinibackend.model.CampagnaVaccinale;
import com.kodikas.appvaccinibackend.model.Vaccino;
import com.kodikas.appvaccinibackend.repository.CampagnaVaccinaleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Set;

@Configuration
public class CanpagnaVaccinaleConfig {
    Vaccino v1 = new Vaccino(
            "sputnik",
            100L
    );
    Vaccino v2 = new Vaccino(
            "jansen",
            100L
    );
    CampagnaVaccinale campagnaVaccinale1 = new CampagnaVaccinale(
            "campagna1",
            Set.of(
                    v1
            )
    );
    CampagnaVaccinale campagnaVaccinale2 = new CampagnaVaccinale(
            "campagna2",
            Set.of(
                    v2
            )
    );

    @Bean
    CommandLineRunner commandLineRunnerCampagna(
            CampagnaVaccinaleRepository repository
    ) {
        return args -> {
            repository.saveAll(
                    List.of(campagnaVaccinale1, campagnaVaccinale2)
            );
        };
    }
}