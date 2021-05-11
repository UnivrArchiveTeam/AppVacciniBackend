package com.kodikas.appvaccinibackend.repository;

import com.kodikas.appvaccinibackend.id.DispId;
import com.kodikas.appvaccinibackend.model.Disponibilita;
import com.kodikas.appvaccinibackend.wrapper.DisponibilitaWrapper;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DisponibilitaRepostitory extends JpaRepository<Disponibilita, DispId> {

    DisponibilitaWrapper findAllByCategoria(String categoria);

    List<Disponibilita> findById_IdVaccino(Long vaccino);

    List<Disponibilita> findAllById_IdVaccino(Long vaccino);

    List<DispId> findAllById(DispId id);



}
