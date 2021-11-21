package com.hackaton.padrino.repositories;

import com.hackaton.padrino.models.pitch;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("pitchRepository")
public interface pitchRepository extends JpaRepository<pitch, Long> {
    
}
