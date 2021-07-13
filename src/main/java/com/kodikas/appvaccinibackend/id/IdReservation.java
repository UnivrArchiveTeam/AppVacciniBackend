package com.kodikas.appvaccinibackend.id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Getter
@Setter
public class IdReservation implements  Serializable{
    private Long idVaccine;
    private String fiscalCode;
}
