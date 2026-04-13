package com.example.projeto_integrador.dtos;

import jakarta.validation.constraints.NotNull;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.List;

public record TratamentoRequestDTO(
        @NotNull(message = "Data de início é obrigatória")
       LocalDate dataInicio,

       LocalDate dataFim,

       String observacao,

        @NotNull(message = "Prontuário é obrigatório")
       Long prontuarioId,

       List<MedicacaoRequestDTO> medicacoes
) {}
