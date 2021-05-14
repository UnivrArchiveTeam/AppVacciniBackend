package com.kodikas.appvaccinibackend.wrapper;

import com.kodikas.appvaccinibackend.model.Availability;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class AvailabilityWrapper {
    private List<Availability> availability;
}
