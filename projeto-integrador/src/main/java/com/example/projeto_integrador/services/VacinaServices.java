package com.example.projeto_integrador.services;

import com.example.projeto_integrador.dtos.VacinaRequestDTO;
import com.example.projeto_integrador.dtos.VacinaResponseDTO;
import com.example.projeto_integrador.model.Prontuario;

import java.util.List;

public interface VacinaServices {

    VacinaResponseDTO cadastrar(VacinaRequestDTO dto);

    VacinaResponseDTO buscarPorId(Long id);

    List<VacinaResponseDTO> listarPorProntuario(Long prontuarioId);

    List<VacinaResponseDTO> listarAtrasadas();
}
