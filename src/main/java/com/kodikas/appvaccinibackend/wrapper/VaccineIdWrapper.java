package com.kodikas.appvaccinibackend.wrapper;

import com.kodikas.appvaccinibackend.model.Vaccine;
import lombok.*;


import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VaccineIdWrapper {
    private List<Long> idVaccines;
}
