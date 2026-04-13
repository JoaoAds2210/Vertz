package com.example.projeto_integrador.dtos;

import java.time.LocalDate;

public record PetResponseDTO(
   Long id,
   String nome,
   String especie,
   String raca,
   LocalDate dataNascimento,
   Long tutorId

) {}
