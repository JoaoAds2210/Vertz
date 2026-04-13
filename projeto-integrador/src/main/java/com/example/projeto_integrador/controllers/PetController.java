package com.example.projeto_integrador.controllers;

import com.example.projeto_integrador.dtos.PetRequestDTO;
import com.example.projeto_integrador.dtos.PetResponseDTO;
import com.example.projeto_integrador.services.PetServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
@RequiredArgsConstructor
public class PetController {

    private final PetServices petServices;

    @PostMapping
    public ResponseEntity<PetResponseDTO> cadastrar(@RequestBody @Valid PetRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(petServices.cadastrar(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(petServices.buscarPorId(id));
    }

    @GetMapping("/tutor/{tutorId}")
    public ResponseEntity<List<PetResponseDTO>> listarPorTutor(@PathVariable Long tutorId) {
        return ResponseEntity.ok(petServices.listarPorTutor(tutorId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        petServices.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
