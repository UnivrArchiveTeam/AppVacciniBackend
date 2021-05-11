package com.kodikas.appvaccinibackend.wrapper;

import com.kodikas.appvaccinibackend.model.Disponibilita;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class DisponibilitaWrapper {
    private List<Disponibilita> disponibilita;
}
