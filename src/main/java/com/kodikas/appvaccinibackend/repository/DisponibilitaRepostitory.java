package com.kodikas.appvaccinibackend.repository;

import com.kodikas.appvaccinibackend.model.Disponibilita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisponibilitaRepostitory extends JpaRepository<Disponibilita,String> {
}
