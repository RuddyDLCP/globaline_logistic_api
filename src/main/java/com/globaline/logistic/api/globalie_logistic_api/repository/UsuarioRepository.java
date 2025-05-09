package com.globaline.logistic.api.globalie_logistic_api.repository;

import com.globaline.logistic.api.globalie_logistic_api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    List<Usuario> findAllByEmail(String email);
}