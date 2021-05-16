package com.kodikas.appvaccinibackend.repository;

import com.kodikas.appvaccinibackend.model.Entitled;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntitledRepository extends JpaRepository<Entitled,Long> {

    List<Entitled> findAllByCategory (String category);
}
