package com.kodikas.appvaccinibackend.wrapper;

import com.kodikas.appvaccinibackend.model.Cittadino;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class CittadinoWrapper {
    private List<Cittadino> cittadini;
}
