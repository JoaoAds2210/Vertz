package com.example.projeto_integrador.Repositories;

import com.example.projeto_integrador.model.Vacina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface VacinaRepository extends JpaRepository<Vacina, Long> {

    List<Vacina> findByProntuarioId(Long prontuarioId);

    List<Vacina> findByProntuarioIdOrderByDataAplicacaoDesc(Long prontuarioId);

    List<Vacina> findByDataProximaDoseLessThanEqual(LocalDate data);
}
