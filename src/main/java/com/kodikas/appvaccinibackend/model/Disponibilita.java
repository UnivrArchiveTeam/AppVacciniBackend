package com.kodikas.appvaccinibackend.model;
import com.kodikas.appvaccinibackend.id.DispId;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Disponibilita {
   @EmbeddedId DispId id;
    private String categoria;

    //Date
    private LocalDate dataInizio;
    private LocalDate dataFine;
    private LocalTime oraInzio;
    private LocalTime oraFine;
    public Disponibilita(String nomeAmbulatorio,Long idVaccino, String categoria, LocalDate dataInizio,
                         LocalDate dataFine, LocalTime oraInzio, LocalTime oraFine) {
        this.id = new DispId(nomeAmbulatorio,idVaccino);
        this.categoria = categoria;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.oraInzio = oraInzio;
        this.oraFine = oraFine;
    }
}