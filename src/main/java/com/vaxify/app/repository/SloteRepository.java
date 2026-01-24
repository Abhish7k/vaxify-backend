package com.vaxify.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaxify.app.entities.Slot;

import org.springframework.stereotype.Repository;

@Repository
public interface SloteRepository extends JpaRepository<Slot, Long>{
    
}
