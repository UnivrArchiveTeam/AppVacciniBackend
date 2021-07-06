package com.kodikas.appvaccinibackend.repository;

import com.kodikas.appvaccinibackend.model.Entitled;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class EntitledRepositoryTest {

	@Autowired
	EntitledRepository underTest;
	private Entitled entry1;
	private String category;

	@BeforeEach
	void setUp() {
		category = "over80";
	}

	@AfterEach
	void tearDown() {
		underTest.deleteAll();
	}

	@Test
	void findAllByCategory_availableData_retrieveData() {
		// given
		entry1 = underTest.save(new Entitled(category));

		// when
		List<Entitled> entitledList = underTest.findAllByCategory(category);

		// then
		then(entitledList).isNotEmpty();
		then(entitledList.get(0)).isEqualTo(entry1);
	}

	@Test
	void findAllByCategory_noAvailableData_retrieveEmptyList() {

		// when
		List<Entitled> entitledList = underTest.findAllByCategory(category);

		// then
		then(entitledList).isEmpty();
	}
}