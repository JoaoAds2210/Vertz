package com.example.projeto_integrador.dtos;

import com.example.projeto_integrador.model.StatusTratamento;

import java.time.LocalDate;
import java.util.List;

public record TratamentoResponseDTO(
   Long id,
   LocalDate dataInicio,
   LocalDate dataFim,
   String observacao,
   StatusTratamento status,
   Long prontuarioId,
   List<MedicacaoResponseDTO> medicacoes
) {}
