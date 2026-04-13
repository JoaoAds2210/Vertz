package com.example.projeto_integrador.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RelatoRequestDTO(

        @NotNull(message = "Prontuário é obrigatório")
        Long prontuarioId,

        @NotBlank(message = "Descrição é obrigatória")
        String descricao,

        @NotBlank(message = "Sintomas são obrigatórios")
        String sintomasObservados,

        String observacoesAdicionais

) {}
