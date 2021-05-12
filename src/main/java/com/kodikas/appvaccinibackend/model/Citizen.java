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
public class Citizen {
    @Id
    private String fiscalCode;
    private Long cardNumber;
    private String name;
    private String surname;
    private String birthPlace;
    private LocalDate dob;
    private String category;
    private boolean registered = false;

    public Citizen(String fiscalCode, Long cardNumber, String name, String surname, String birthPlace, LocalDate dob, String category) {
        this.fiscalCode = fiscalCode;
        this.cardNumber = cardNumber;
        this.name = name;
        this.surname = surname;
        this.birthPlace = birthPlace;
        this.dob = dob;
        this.category = category;
    }
}
