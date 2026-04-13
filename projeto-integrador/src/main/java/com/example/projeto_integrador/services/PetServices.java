package com.example.projeto_integrador.services;

import com.example.projeto_integrador.dtos.PetRequestDTO;
import com.example.projeto_integrador.dtos.PetResponseDTO;

import java.util.List;

public interface PetServices {
    PetResponseDTO cadastrar(PetRequestDTO dto);

    PetResponseDTO buscarPorId(Long id);

    List<PetResponseDTO> listarPorTutor(Long tutorId);

    void deletar(Long id);
}
