package com.kodikas.appvaccinibackend.repository;

import com.kodikas.appvaccinibackend.model.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface VaccineRepository extends JpaRepository<Vaccine, Long> {
    List<Vaccine> findAllByVaccineID(Long id);
    Vaccine findByVaccineID(Long vaccineID);
}
