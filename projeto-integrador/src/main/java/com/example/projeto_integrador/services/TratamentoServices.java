package com.example.projeto_integrador.services;

import com.example.projeto_integrador.dtos.TratamentoRequestDTO;
import com.example.projeto_integrador.dtos.TratamentoResponseDTO;

import java.util.List;

public interface TratamentoServices {

    TratamentoResponseDTO cadastrar(TratamentoRequestDTO dto);

    TratamentoResponseDTO buscarPorId(Long id);

    List<TratamentoResponseDTO> listarPorProntuario(Long prontuarioId);

    List<TratamentoResponseDTO> listarAtivosPorProntuario(Long prontuarioId);

    void finalizarTratamento(Long id);

    void cancelarTratamento(Long id);
}
