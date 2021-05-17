package com.kodikas.appvaccinibackend.wrapper;

import com.kodikas.appvaccinibackend.model.Citizen;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CitizenWrapper {
    private List<Citizen> citizens;
}
