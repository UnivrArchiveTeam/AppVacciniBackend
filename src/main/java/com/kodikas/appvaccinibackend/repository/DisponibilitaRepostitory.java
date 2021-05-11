package com.kodikas.appvaccinibackend.repository;

import com.kodikas.appvaccinibackend.id.DispId;
import com.kodikas.appvaccinibackend.model.Disponibilita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisponibilitaRepostitory extends JpaRepository<Disponibilita, DispId> {

    List<Disponibilita> findAllByCategoria(String categoria);

    List<Disponibilita> findById_IdVaccino(Long vaccino);

    List<Disponibilita> findAllById_IdVaccino(Long vaccino);

//    List<DispId> findAllById(DispId id);



}
