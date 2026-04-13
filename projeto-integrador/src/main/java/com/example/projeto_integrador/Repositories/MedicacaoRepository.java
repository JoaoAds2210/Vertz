package com.example.projeto_integrador.Repositories;

import com.example.projeto_integrador.model.Medicacao;
import com.example.projeto_integrador.model.StatusTratamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicacaoRepository extends JpaRepository<Medicacao, Long> {

    List<Medicacao> findByTratamentoId(Long tratamentoId);

    List<Medicacao> findByTratamentoProntuarioIdAndTratamentoStatus(
            Long prontuarioId,
            StatusTratamento status
    );
}
