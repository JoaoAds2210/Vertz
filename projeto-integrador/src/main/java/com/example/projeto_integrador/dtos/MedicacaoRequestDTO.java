package com.example.projeto_integrador.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record MedicacaoRequestDTO(
        @NotBlank(message = "O nome do medicamento é obrigatório")
        String nomeMedicacao,
        @NotBlank(message = "A dosagem é obrigatória")
        String dosagem,
        @NotBlank(message = "A frequência é obrigatória")
        String frequencia,
        @NotNull(message = "A data de início é obrigatória")
        LocalDate dataInicio,
        LocalDate dataFim,
        Long tratamentoId
) {}
