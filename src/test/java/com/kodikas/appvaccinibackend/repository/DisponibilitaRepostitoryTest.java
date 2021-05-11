package com.kodikas.appvaccinibackend.repository;

import com.kodikas.appvaccinibackend.model.Disponibilita;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@ExtendWith(SpringExtension.class)
@DataJpaTest
class DisponibilitaRepostitoryTest {
    @Autowired private DisponibilitaRepostitory underTest;
    Disponibilita disponibilita;
    String nomeAmbulatorio;
    Long idVaccino;
    String categoria;

    @Test
    void injectedComponentsAreNotNull(){
        assertThat(underTest).isNotNull();
    }

    @BeforeEach
    void setUp() {
        nomeAmbulatorio = "Golosine";
        idVaccino = 1L;
        categoria = "over80";

        disponibilita = new Disponibilita(
                nomeAmbulatorio,idVaccino,categoria,
                LocalDate.of(2021,05,06),LocalDate.of(2021,05,21),
                LocalTime.of(9,00),LocalTime.of(12,00));
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void findAllByCategoria() {
    }

    @Test
    void findById_IdVaccino() {
    }

    @Test
    void itShoudRetrievefindAllById_IdVaccino() {
        underTest.save(disponibilita);
        List<Disponibilita> result = underTest.findAllById_IdVaccino(idVaccino);
        assertThat(result.isEmpty()).isFalse();
    }

    @Test
    void findAllById_NomeAmbulatorio() {
    }
}