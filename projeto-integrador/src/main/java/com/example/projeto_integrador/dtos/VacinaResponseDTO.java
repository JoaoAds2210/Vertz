package com.example.projeto_integrador.dtos;

import java.time.LocalDate;

public record VacinaResponseDTO(

        Long id,
        String nome,
        LocalDate dataAplicacao,
        LocalDate dataProximaDose,
        Long prontuarioId

) {}
