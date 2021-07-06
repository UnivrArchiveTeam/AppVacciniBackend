package com.kodikas.appvaccinibackend.repository;

import com.kodikas.appvaccinibackend.model.Vaccine;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class VaccineRepositoryTest {
	@Autowired
	private VaccineRepository underTest;
	private Vaccine vaccine;

	@BeforeEach
	void setUp() {
		this.vaccine = underTest.save(
				new Vaccine(
						"jansen",
						100L
				)
		);
	}

	@AfterEach
	void tearDown() {
		underTest.deleteAll();
	}

	@Test
	void findByVaccineID_existingVaccine_retrieveCorrectVaccine() {
		// given
		Vaccine result = underTest.findByVaccineID(vaccine.getVaccineID());

		// then
		then(result).isEqualTo(vaccine);
	}

	@Test
	void findByVaccineID_noVaccineAvailable_retrieveCorrectVaccine() {
		// given
		underTest.deleteAll();

		// when
		Vaccine result = underTest.findByVaccineID(vaccine.getVaccineID());

		// then
		then(result).isNull();
	}
}