package com.example.projeto_integrador.services;

import com.example.projeto_integrador.Repositories.ProntuarioRepository;
import com.example.projeto_integrador.Repositories.VacinaRepository;
import com.example.projeto_integrador.dtos.VacinaRequestDTO;
import com.example.projeto_integrador.dtos.VacinaResponseDTO;
import com.example.projeto_integrador.model.Prontuario;
import com.example.projeto_integrador.model.Vacina;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VacinaServicesImp implements VacinaServices {

    private final VacinaRepository vacinaRepository;
    private final ProntuarioRepository prontuarioRepository;

    @Override
    public VacinaResponseDTO cadastrar(VacinaRequestDTO dto){

        Prontuario prontuario = prontuarioRepository.findById(dto.prontuarioId())
                .orElseThrow(() -> new RuntimeException("Prontuario não encontrado"));

        if (dto.dataProximaDose() != null && dto.dataProximaDose().isBefore(dto.dataAplicacao())) {
            throw new RuntimeException("Data da próxima dose não pode ser anterior à data de aplicação");
        }

        Vacina vacina = Vacina.builder()
                .nome(dto.nome())
                .dataAplicacao(dto.dataAplicacao())
                .dataProximaDose(dto.dataProximaDose())
                .prontuario(prontuario)
                .ativa(true)
                .build();

        vacina = vacinaRepository.save(vacina);

        return toResponse(vacina);

    }

    @Override
    public VacinaResponseDTO buscarPorId(Long id) {
        Vacina vacina = vacinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vacina não encontrada"));
        return toResponse(vacina);
    }

    @Override
    public List<VacinaResponseDTO> listarPorProntuario(Long prontuarioId) {
        return vacinaRepository.findByProntuarioIdOrderByDataAplicacaoDesc(prontuarioId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public List<VacinaResponseDTO> listarAtrasadas() {
        return vacinaRepository.findByDataProximaDoseLessThanEqual(LocalDate.now())
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private VacinaResponseDTO toResponse(Vacina v) {
        return new VacinaResponseDTO(
                v.getId(),
                v.getNome(),
                v.getDataAplicacao(),
                v.getDataProximaDose(),
                v.getProntuario().getId()
        );
    }
}
