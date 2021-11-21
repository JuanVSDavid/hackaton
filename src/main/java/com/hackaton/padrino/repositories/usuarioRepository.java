package com.hackaton.padrino.repositories;

import com.hackaton.padrino.models.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("usuarioRepository")
public interface usuarioRepository extends JpaRepository<usuario, Long> {
    @Query(value = "SELECT * FROM usuarios u WHERE u.username = :username", nativeQuery = true)
    usuario findbyUsername(String username);
}
