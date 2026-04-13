package com.example.projeto_integrador.services;

import com.example.projeto_integrador.Repositories.ProntuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProntuarioServicesImpl implements ProntuarioServices {

    @Autowired
    private ProntuarioRepository prontuarioRepository;

    @Override
    public Long buscarIdPorPet(Long petId) {
        return prontuarioRepository.findByPetId(petId)
                .orElseThrow(() -> new RuntimeException("Pet nao encontrado"))
                .getId();
    }

    @Override
    public boolean existePorPet(Long petId) {
        return prontuarioRepository.findByPetId(petId).isPresent();
    }
}
