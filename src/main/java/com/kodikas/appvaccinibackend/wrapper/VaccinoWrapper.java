package com.kodikas.appvaccinibackend.wrapper;

import com.kodikas.appvaccinibackend.model.Vaccino;
import lombok.AllArgsConstructor;
import lombok.Data;


import java.util.List;

@AllArgsConstructor
@Data
public class VaccinoWrapper {
    private List<Vaccino> vaccini;
}
