package com.example.projeto_integrador.services;

import com.example.projeto_integrador.dtos.TutorRequestDTO;
import com.example.projeto_integrador.dtos.TutorResponseDTO;


import java.util.List;

public interface TutorServices {

    TutorResponseDTO cadastrar(TutorRequestDTO dto);

    TutorResponseDTO buscarPorId(Long id);

    List<TutorResponseDTO> listarTodos();

    void deletarTutor(Long id);
}
