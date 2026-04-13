package com.example.projeto_integrador.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PetRequestDTO(
        @NotBlank(message = "Nome é obrigatório")
       String nome,

       @NotBlank
       String especie,

       String raca,

       LocalDate dataNascimento,

       @NotNull
       Long tutorId
) {}
