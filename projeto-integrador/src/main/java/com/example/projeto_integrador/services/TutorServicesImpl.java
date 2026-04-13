package com.example.projeto_integrador.services;

import com.example.projeto_integrador.Repositories.TutorRepository;
import com.example.projeto_integrador.dtos.TutorRequestDTO;
import com.example.projeto_integrador.dtos.TutorResponseDTO;
import com.example.projeto_integrador.model.Tutor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TutorServicesImpl implements TutorServices {

    private final TutorRepository tutorRepository;

    @Override
    @Transactional
    public TutorResponseDTO cadastrar(TutorRequestDTO dto) {

        if (tutorRepository.existsByEmail(dto.email())){
            throw new RuntimeException("E-mail já cadastrado");
        }

        //Ele permite construir objetos de forma mais legível e segura, sem precisar de construtores enormes.
        Tutor tutor = Tutor.builder()
                .nome(dto.nome())
                .email(dto.email())
                .telefone(dto.telefone())
                .build();

        tutor = tutorRepository.save(tutor);

        return toResponse(tutor);
    }

    @Override
    public TutorResponseDTO buscarPorId(Long id) {

        Tutor tutor = tutorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tutor nao encontrado"));

        return toResponse(tutor);
    }

    @Override
    public List<TutorResponseDTO> listarTodos() {
        return tutorRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public void deletarTutor(Long id) {
        if (!tutorRepository.existsById(id)){
            throw new RuntimeException("Tutor nao encontrado");
        }
        tutorRepository.deleteById(id);
    }

    private TutorResponseDTO toResponse(Tutor tutor) {
        return new TutorResponseDTO(
                tutor.getId(),
                tutor.getNome(),
                tutor.getEmail(),
                tutor.getTelefone()
        );
    }
}
