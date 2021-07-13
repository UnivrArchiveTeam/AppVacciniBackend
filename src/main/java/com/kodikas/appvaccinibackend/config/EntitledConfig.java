//package com.kodikas.appvaccinibackend.config;
//
//import com.kodikas.appvaccinibackend.model.Availability;
//import com.kodikas.appvaccinibackend.model.Entitled;
//import com.kodikas.appvaccinibackend.model.Vaccine;
//import com.kodikas.appvaccinibackend.repository.AvailabilityRepository;
//import com.kodikas.appvaccinibackend.repository.EntitledRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.List;
//
//@Configuration
//public class EntitledConfig {
//    @Bean
//    CommandLineRunner commandLineRunnerEntitled(EntitledRepository repository){
//        return  args -> {
//            Entitled entry1 = new Entitled("over 80", new Vaccine(2341244L,
//                    "sputnik",
//                    100L
//            ));
//            repository.saveAll(List.of(entry1));
//        };
//    }
//}
