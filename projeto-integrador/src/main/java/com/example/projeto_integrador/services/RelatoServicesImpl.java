package com.example.projeto_integrador.services;


import com.example.projeto_integrador.Repositories.PetRepository;
import com.example.projeto_integrador.Repositories.ProntuarioRepository;
import com.example.projeto_integrador.Repositories.RelatoRepository;
import com.example.projeto_integrador.dtos.RelatoRequestDTO;
import com.example.projeto_integrador.dtos.RelatoResponseDTO;
import com.example.projeto_integrador.model.Prontuario;
import com.example.projeto_integrador.model.Relato;
import com.example.projeto_integrador.model.StatusRelato;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class RelatoServicesImpl implements RelatoServices {

    private final RelatoRepository relatoRepository;
    private final ProntuarioRepository prontuarioRepository;

    @Override
    @Transactional
    public RelatoResponseDTO cadastrar(RelatoRequestDTO dto) {

        Prontuario prontuario = prontuarioRepository.findById(dto.prontuarioId())
                .orElseThrow(() -> new RuntimeException("Prontuario nao encontrado com esse ID"));

        Relato relato = Relato.builder()
                .prontuario(prontuario)
                .descricao(dto.descricao())
                .sintomasObservados(dto.sintomasObservados())
                .observacoesAdicionais(dto.observacoesAdicionais())
                .dataHoraRelato(LocalDateTime.now())
                .status(StatusRelato.ABERTO)
                .build();

        relato = relatoRepository.save(relato);

        return mapToResponse(relato);

    }

    @Override
    public RelatoResponseDTO buscarPorId(Long id) {
        Relato relato = relatoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Relato nao encontrado com esse ID"));

        return mapToResponse(relato);
    }

    @Override
    public List<RelatoResponseDTO> listarPorStatus(Long prontuarioId, StatusRelato status) {

        return relatoRepository.findByProntuarioIdAndStatus(prontuarioId, status)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public RelatoResponseDTO atualizarStatus(Long id, StatusRelato status) {

        Relato relato = relatoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Relato nao encontrado com esse ID"));

        relato.setStatus(status);

        relato = relatoRepository.save(relato);

        return mapToResponse(relato);
    }

    private RelatoResponseDTO mapToResponse(Relato r) {
        return new RelatoResponseDTO(
                r.getId(),
                r.getDataHoraRelato(),
                r.getDescricao(),
                r.getSintomasObservados(),
                r.getObservacoesAdicionais(),
                r.getStatus()
        );
    }
}
