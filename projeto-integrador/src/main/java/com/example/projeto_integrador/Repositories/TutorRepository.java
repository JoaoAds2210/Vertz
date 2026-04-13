package com.example.projeto_integrador.Repositories;

import com.example.projeto_integrador.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TutorRepository extends JpaRepository<Tutor, Long> {

    Optional<Tutor> findByEmail(String email);

    boolean existsByEmail(String email);

}
