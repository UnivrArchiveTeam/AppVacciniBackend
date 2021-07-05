package com.kodikas.appvaccinibackend.service;

import com.kodikas.appvaccinibackend.model.Entitled;
import com.kodikas.appvaccinibackend.model.Vaccine;
import com.kodikas.appvaccinibackend.repository.EntitledRepository;
import com.kodikas.appvaccinibackend.repository.VaccineRepository;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EntitledServiceUnitTest {

	@Mock
	private EntitledRepository entitledRepository;
	@Mock
	private VaccineRepository vaccineRepository;
	private EntitledService underTest;
	private Entitled entry1;
	private Entitled entry2;

	@BeforeEach
	void setUp() {
		underTest = new EntitledService(entitledRepository, vaccineRepository);

		entry1 = new Entitled("over80");
		entry2 = new Entitled("over50");
	}

	@Test
	void getAllEntitled_availableEntitles_pass() {
		// given
		List<Entitled> entitledList = List.of(entry1, entry2);
		BDDMockito.given(entitledRepository.findAll()).willReturn(entitledList);

		// when
		underTest.getAllEntitled();

		BDDMockito.then(entitledRepository).should(times(1)).findAll();
	}

	@Test
	void addEntitled_noVaccine_pass() {
		// when
		underTest.addEntitled(entry1);

		// then
		BDDMockito.then(entitledRepository).should(times(1)).save(entry1);
	}

	@Test
	void addEntitled_existingVaccine_pass() {
		// given
		Vaccine vaccine = new Vaccine(
				1L,
				"jansen",
				100L
		);
		entry1.setVaccine(vaccine);
		BDDMockito.given(vaccineRepository.findById(any())).willReturn(Optional.of(vaccine));

		// when
		underTest.addEntitled(entry1);

		// then
		BDDMockito.then(entitledRepository).should(times(1)).save(entry1);
	}

	@Test
	void getEntitledByCategory_availableEntitles_pass() {
		// given
		List<Entitled> list_entitled = List.of(entry1);
		String category = "over80";
		BDDMockito.given(entitledRepository.findAllByCategory(category)).willReturn(list_entitled);

		// when
		List<Entitled> result = underTest.getEntitledByCategory(category);

		// then
		boolean check = false;

		for (Entitled find : result) {
			if (!(find.getCategory().equals(category))) {
				check = true;
			}
		}

		BDDAssertions.then(check).isFalse();
	}

	@Test
	// TODO: controlla se rimuovere
	void getEntitledByCategory_unavailableCategory_throwException() {
		// given
		String category = "under40";

		// when
		Throwable throwable = catchThrowable(() -> underTest.getEntitledByCategory(category));

		// then
		BDDAssertions.then(throwable)
				.isInstanceOf(IllegalStateException.class)
				.hasMessage("I have not found anyone entitled to this category");

		BDDMockito.then(entitledRepository)
				.should(times(1))
				.findAllByCategory(any());
	}

	@Test
	void getCategories_availableEntitles_pass() {
		// given
		List<String> expected = List.of(
				"over80",
				"over50"
		);
		BDDMockito.given(entitledRepository.findAll()).willReturn(
				List.of(
						entry1,
						entry2
				)
		);

		// when
		List<String> categories = underTest.getCategories();
		BDDAssertions.then(categories).isEqualTo(expected);
	}
}