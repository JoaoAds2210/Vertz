package com.example.projeto_integrador.services;

import com.example.projeto_integrador.Repositories.PetRepository;
import com.example.projeto_integrador.Repositories.ProntuarioRepository;
import com.example.projeto_integrador.Repositories.TutorRepository;
import com.example.projeto_integrador.dtos.PetRequestDTO;
import com.example.projeto_integrador.dtos.PetResponseDTO;
import com.example.projeto_integrador.model.Pet;
import com.example.projeto_integrador.model.Prontuario;
import com.example.projeto_integrador.model.Tutor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PetServicesImpl implements PetServices {

    private final PetRepository petRepository;
    private final TutorRepository tutorRepository;
    private final ProntuarioRepository prontuarioRepository;

    @Override
    @Transactional
    public PetResponseDTO cadastrar(PetRequestDTO dto) {

        Tutor tutor = tutorRepository.findById(dto.tutorId())
                .orElseThrow(() -> new RuntimeException("Tutor não encontrado"));


        Pet pet = Pet.builder()
                .nome(dto.nome())
                .especie(dto.especie())
                .raca(dto.raca())
                .dataNascimento(dto.dataNascimento())
                .tutor(tutor)
                .build();

        pet = petRepository.save(pet);

        Prontuario prontuario = Prontuario.builder()
                .pet(pet)
                .ativo(true)
                .build();

        prontuarioRepository.save(prontuario);

        return toResponse(pet);
    }

    @Override
    public PetResponseDTO buscarPorId(Long id) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet não encontrado"));

        return toResponse(pet);
    }

    @Override
    public List<PetResponseDTO> listarPorTutor(Long tutorId) {
        return petRepository.findByTutorId(tutorId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public void deletar(Long id) {
        petRepository.deleteById(id);
    }

    private PetResponseDTO toResponse(Pet pet){
        return new PetResponseDTO(
                pet.getId(),
                pet.getNome(),
                pet.getEspecie(),
                pet.getRaca(),
                pet.getDataNascimento(),
                pet.getTutor().getId()
        );
    }
}
