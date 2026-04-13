package com.example.projeto_integrador.services;

import com.example.projeto_integrador.dtos.RelatoRequestDTO;
import com.example.projeto_integrador.dtos.RelatoResponseDTO;
import com.example.projeto_integrador.model.StatusRelato;

import java.util.List;

public interface RelatoServices {

    RelatoResponseDTO cadastrar(RelatoRequestDTO dto);

    RelatoResponseDTO buscarPorId(Long id);

    List<RelatoResponseDTO> listarPorStatus(Long petId, StatusRelato status);

    RelatoResponseDTO atualizarStatus(Long id, StatusRelato status);
}
