package com.example.projeto_integrador.services;

import com.example.projeto_integrador.Repositories.ProntuarioRepository;
import com.example.projeto_integrador.Repositories.TratamentoRepository;
import com.example.projeto_integrador.dtos.TratamentoRequestDTO;
import com.example.projeto_integrador.dtos.TratamentoResponseDTO;
import com.example.projeto_integrador.model.Prontuario;
import com.example.projeto_integrador.model.StatusTratamento;
import com.example.projeto_integrador.model.Tratamento;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TratamentoServicesImpl implements TratamentoServices {

    private final TratamentoRepository tratamentoRepository;
    private final ProntuarioRepository prontuarioRepository;

    @Override
    @Transactional
    public TratamentoResponseDTO cadastrar(TratamentoRequestDTO dto) {

        Prontuario prontuario = prontuarioRepository.findById(dto.prontuarioId())
                .orElseThrow(() -> new RuntimeException("Prontuário não encontrado"));

        if (dto.dataFim() != null && dto.dataFim().isBefore(dto.dataInicio())) {
            throw new RuntimeException("Data fim não pode ser antes da data início");
        }

        Tratamento tratamento = Tratamento.builder()
                .dataInicio(dto.dataInicio())
                .dataFim(dto.dataFim())
                .observacao(dto.observacao())
                .status(StatusTratamento.ATIVO)
                .prontuario(prontuario)
                .build();

        tratamento = tratamentoRepository.save(tratamento);
        return mapToResponse(tratamento);
    }

    @Override
    public TratamentoResponseDTO buscarPorId(Long id) {

        Tratamento tratamento = tratamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tratamento não encontrado"));

        return mapToResponse(tratamento);
    }

    @Override
    public List<TratamentoResponseDTO> listarPorProntuario(Long prontuarioId) {

        return tratamentoRepository.findByProntuarioId(prontuarioId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<TratamentoResponseDTO> listarAtivosPorProntuario(Long prontuarioId) {

        return tratamentoRepository
                .findByProntuarioIdAndStatus(prontuarioId, StatusTratamento.ATIVO)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional
    public void finalizarTratamento(Long id) {

        Tratamento tratamento = tratamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tratamento não encontrado"));

        if (tratamento.getStatus() == StatusTratamento.FINALIZADO) {
            throw new RuntimeException("Tratamento já está finalizado");
        }

        tratamento.setStatus(StatusTratamento.FINALIZADO);

        tratamentoRepository.save(tratamento);
    }

    @Override
    @Transactional
    public void cancelarTratamento(Long id) {

        Tratamento tratamento = tratamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tratamento não encontrado"));

        if (tratamento.getStatus() == StatusTratamento.FINALIZADO) {
            throw new RuntimeException("Tratamento já finalizado não pode ser cancelado");
        }
        if (tratamento.getStatus() == StatusTratamento.CANCELADO) {
            throw new RuntimeException("Tratamento já está cancelado");
        }

        tratamento.setStatus(StatusTratamento.CANCELADO);
        tratamentoRepository.save(tratamento);
    }

    private TratamentoResponseDTO mapToResponse(Tratamento t) {
        return new TratamentoResponseDTO(
                t.getId(),
                t.getDataInicio(),
                t.getDataFim(),
                t.getObservacao(),
                t.getStatus(),
                t.getProntuario().getId(),
                List.of()
        );
    }
}