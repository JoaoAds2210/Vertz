package com.example.projeto_integrador.dtos;

import com.example.projeto_integrador.model.StatusRelato;

import java.time.LocalDateTime;

public record RelatoResponseDTO(
   Long id,
   LocalDateTime dataHoraRelato,
   String descricao,
   String sintomasObservados,
   String observacoesAdicionais,
   StatusRelato statusRelato
) {}
