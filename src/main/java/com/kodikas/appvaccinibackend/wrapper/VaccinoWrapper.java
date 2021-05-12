package com.kodikas.appvaccinibackend.wrapper;

import com.kodikas.appvaccinibackend.model.Vaccine;
import lombok.AllArgsConstructor;
import lombok.Data;


import java.util.List;

@AllArgsConstructor
@Data
public class VaccinoWrapper {
    private List<Vaccine> vaccini;
}
