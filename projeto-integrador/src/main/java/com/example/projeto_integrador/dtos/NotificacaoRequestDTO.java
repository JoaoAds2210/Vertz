package com.example.projeto_integrador.dtos;

import com.example.projeto_integrador.model.StatusNotificacao;
import com.example.projeto_integrador.model.TipoNotificacao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record NotificacaoRequestDTO(
        @NotBlank
        String titulo,
        @NotBlank
        String mensagem,
        @NotNull
        LocalDateTime dataAgendada,
        @NotNull
        TipoNotificacao tipo,
        @NotNull
        Long petId,
        StatusNotificacao status
) {}
