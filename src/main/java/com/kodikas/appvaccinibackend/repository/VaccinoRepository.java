package com.kodikas.appvaccinibackend.repository;

import com.kodikas.appvaccinibackend.model.Vaccino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccinoRepository extends JpaRepository<Vaccino, Long> {
}
