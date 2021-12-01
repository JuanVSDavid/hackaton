package com.hackaton.padrino.repositories;

import java.util.List;

import com.hackaton.padrino.models.contribucion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("contribucionRepository")
public interface contribucionRepository extends JpaRepository<contribucion, Long>{
    @Query(value = "SELECT * FROM contribuciones c WHERE c.id_pitch = ?1", nativeQuery = true)
    List<contribucion> ListForId_Pitch(Long id_pitch);
    @Query(value = "SELECT * FROM contribuciones c WHERE c.id_padrino = ?1", nativeQuery = true)
    List<contribucion> ListForId_Usuario(Long id_padrino);
}
