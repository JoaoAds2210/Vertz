package com.example.projeto_integrador.Repositories;

import com.example.projeto_integrador.model.Prontuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProntuarioRepository extends JpaRepository<Prontuario, Long> {


    Optional<Prontuario> findByPetId(Long petId);
}
