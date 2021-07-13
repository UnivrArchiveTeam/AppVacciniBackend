package com.kodikas.appvaccinibackend.model;

import com.kodikas.appvaccinibackend.id.IdNotification;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

	@EmbeddedId
	private IdNotification idNotification;
	private LocalDate startDate;
	private LocalDate endDate;
}