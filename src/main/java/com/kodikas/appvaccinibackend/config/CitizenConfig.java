package com.kodikas.appvaccinibackend.config;

import com.kodikas.appvaccinibackend.model.Citizen;
import com.kodikas.appvaccinibackend.repository.CittadinoRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Configuration
public class CitizenConfig {
    CSVReader csvReader;
    File file;
    List<Citizen> citizens = new ArrayList<>();

    public CitizenConfig() {
        try {
            file = ResourceUtils.getFile("classpath:fakeCitadini.csv");
            csvReader = new CSVReader(new FileReader(file));
            List<String[]> r = csvReader.readAll();
            r.forEach(x -> {
                        if (!x[0].equals("Row")) {
                            citizens.add(
                                    new Citizen(
                                            x[6],
                                            Long.parseLong(x[7]),
                                            x[1],
                                            x[2],
                                            x[5],
                                            LocalDate.parse(x[4]),
                                            getCategory(x[4])
                                    )
                            );
                        }
                    }
            );
        } catch (CsvException | IOException e) {
            e.printStackTrace();
        }
    }

    private static String getCategory(String dataDiNascita) {
        List<String> categories = List.of("paziente oncologico", "paziente iperteso", "paziente a rischio");
        LocalDate dob = LocalDate.parse(dataDiNascita);
        int age = Period.between(dob, LocalDate.now()).getYears();

        if (age>=70 && age<=79)
            return "età 70-79";
        else if (age >= 80)
            return "over 80";
        else
            return categories.get(new Random().nextInt(categories.size()));
    }

    @Bean
    CommandLineRunner commandLineRunnerCitizen(
            CittadinoRepository repository
    ) {
        return args -> repository.saveAll(
                citizens
        );
    }
}
