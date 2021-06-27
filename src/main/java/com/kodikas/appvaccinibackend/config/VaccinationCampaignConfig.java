//package com.kodikas.appvaccinibackend.config;
//
//import com.kodikas.appvaccinibackend.model.VaccinationCampaign;
//import com.kodikas.appvaccinibackend.model.Vaccine;
//import com.kodikas.appvaccinibackend.repository.VaccinationCampaignRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.List;
//import java.util.Set;
//
//@Configuration
//public class VaccinationCampaignConfig {
//    Vaccine v1 = new Vaccine(
//            "sputnik",
//            100L
//    );
//    Vaccine v2 = new Vaccine(
//            "jansen",
//            100L
//    );
//    VaccinationCampaign vaccinationCampaign1 = new VaccinationCampaign(
//            "COVID 19",
//            Set.of(
//                    v1
//            )
//    );
//    VaccinationCampaign vaccinationCampaign2 = new VaccinationCampaign(
//            "campagna2",
//            Set.of(
//                    v2
//            )
//    );
//
//    @Bean
//    CommandLineRunner commandLineRunnerCampaign(
//            VaccinationCampaignRepository repository
//    ) {
//        return args -> repository.saveAll(
//                List.of(vaccinationCampaign1, vaccinationCampaign2)
//        );
//    }
//}