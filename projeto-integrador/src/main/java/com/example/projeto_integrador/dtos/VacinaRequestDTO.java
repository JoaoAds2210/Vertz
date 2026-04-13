package com.example.projeto_integrador.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record VacinaRequestDTO(

        @NotBlank(message = "Nome da vacina é obrigatório")
        String nome,

        @NotNull(message = "Data de aplicação é obrigatória")
        LocalDate dataAplicacao,

        LocalDate dataProximaDose,

        @NotNull(message = "Prontuário é obrigatório")
        Long prontuarioId

) {}