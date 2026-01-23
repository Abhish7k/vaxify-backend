package com.vaxify.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaxify.app.entities.Vaccine;


public interface  VaccineRepository extends JpaRepository<Vaccine, Long> {
    
}
