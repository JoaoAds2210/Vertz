package com.example.projeto_integrador.dtos;

import com.example.projeto_integrador.model.StatusNotificacao;
import com.example.projeto_integrador.model.TipoNotificacao;

import java.time.LocalDateTime;

public record NotificacaoResponseDTO(
   Long id,
   String titulo,
   String mensagem,
   LocalDateTime dataAgendada,
   TipoNotificacao tipo,
   StatusNotificacao status
) {}
