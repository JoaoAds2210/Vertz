package com.example.projeto_integrador.dtos;

import java.time.LocalDate;

public record MedicacaoResponseDTO(
        Long id,
        String nomeMedicacao,
        String dosagem,
        String frequencia,
        LocalDate dataInicio,
        LocalDate dataFim
) {}
