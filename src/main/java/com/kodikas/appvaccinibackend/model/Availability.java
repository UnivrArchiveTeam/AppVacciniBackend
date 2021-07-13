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
	IdAvailability availabilityId;
	//Date
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalTime startHour;
	private LocalTime endHour;

	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn
	@MapsId("idVaccine")
	private Vaccine vaccine;

	public Availability(String clinicName, Long idVaccine, LocalDate startDate,
						LocalDate endDate, LocalTime startHour, LocalTime endHour) {
		this.availabilityId = new IdAvailability(clinicName, idVaccine);
		this.startDate = startDate;
		this.endDate = endDate;
		this.startHour = startHour;
		this.endHour = endHour;
	}

	public Availability(String clinicName, Long idVaccine, LocalDate startDate,
						LocalDate endDate, LocalTime startHour, LocalTime endHour,
						Vaccine vaccine) {
		this.availabilityId = new IdAvailability(clinicName, idVaccine);
		this.startDate = startDate;
		this.endDate = endDate;
		this.startHour = startHour;
		this.endHour = endHour;
		this.vaccine = vaccine;
	}
}