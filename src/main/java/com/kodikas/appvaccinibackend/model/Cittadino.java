package com.kodikas.appvaccinibackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Cittadino {
    @Id
    private String codiceFiscale;
    private Long numeroTessera;
    private String nome;
    private String cognome;
    private String luogoDiNascita;
    private LocalDate dataDiNascita;
    private String categoria;
    private boolean registrato = false;

    public Cittadino(String codiceFiscale, Long numeroTessera, String nome, String cognome, String luogoDiNascita, LocalDate dataDiNascita, String categoria) {
        this.codiceFiscale = codiceFiscale;
        this.numeroTessera = numeroTessera;
        this.nome = nome;
        this.cognome = cognome;
        this.luogoDiNascita = luogoDiNascita;
        this.dataDiNascita = dataDiNascita;
        this.categoria = categoria;
    }
}
