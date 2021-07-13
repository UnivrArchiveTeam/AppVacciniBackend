package com.kodikas.appvaccinibackend.wrapper;

import com.kodikas.appvaccinibackend.model.Entitled;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EntitledWrapper {
    List<Entitled> entitles;
}
