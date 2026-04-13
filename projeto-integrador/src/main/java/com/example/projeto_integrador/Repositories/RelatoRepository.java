package com.example.projeto_integrador.Repositories;

import com.example.projeto_integrador.model.Relato;
import com.example.projeto_integrador.model.StatusRelato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RelatoRepository extends JpaRepository<Relato, Long> {

    List<Relato> findByProntuarioIdOrderByDataHoraRelatoDesc(Long prontuarioId);

    List<Relato> findByProntuarioIdAndStatus(Long prontuarioId, StatusRelato status);
}
