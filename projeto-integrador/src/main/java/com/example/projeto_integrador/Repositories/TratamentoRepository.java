package com.example.projeto_integrador.Repositories;

import com.example.projeto_integrador.model.StatusTratamento;
import com.example.projeto_integrador.model.Tratamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TratamentoRepository extends JpaRepository<Tratamento, Long> {

    List<Tratamento> findByProntuarioId(Long prontuarioId);

    List<Tratamento> findByProntuarioIdAndStatus(Long prontuarioId, StatusTratamento status);

    Optional<Tratamento> findFirstByProntuarioIdAndStatusOrderByDataInicioDesc(
            Long prontuarioId,
            StatusTratamento status
    );
}
