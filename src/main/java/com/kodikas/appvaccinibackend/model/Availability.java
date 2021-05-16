package com.kodikas.appvaccinibackend.model;
import com.kodikas.appvaccinibackend.id.IdAvailability;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Availability {
   @EmbeddedId
   IdAvailability id;
    //Date
    private LocalDate start_date;
    private LocalDate end_date;
    private LocalTime start_hour;
    private LocalTime end_hour;
    public Availability(String clinicname, Long idVaccine, LocalDate start_date,
                        LocalDate end_date, LocalTime start_hour, LocalTime end_hour) {
        this.id = new IdAvailability(clinicname,idVaccine);
        this.start_date = start_date;
        this.end_date = end_date;
        this.start_hour = start_hour;
        this.end_hour = end_hour;
    }
}