package com.kodikas.appvaccinibackend.wrapper;

import com.kodikas.appvaccinibackend.model.Entitled;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class EntitledWrapper {
    List<Entitled> entitleds;
}
