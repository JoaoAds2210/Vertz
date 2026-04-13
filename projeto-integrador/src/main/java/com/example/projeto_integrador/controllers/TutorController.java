package com.example.projeto_integrador.controllers;

import com.example.projeto_integrador.dtos.TutorRequestDTO;
import com.example.projeto_integrador.dtos.TutorResponseDTO;
import com.example.projeto_integrador.services.TutorServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tutores")
@RequiredArgsConstructor
public class TutorController {

    private final TutorServices tutorServices;

    @PostMapping
    public ResponseEntity<TutorResponseDTO> cadastrar(@RequestBody @Valid TutorRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tutorServices.cadastrar(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TutorResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(tutorServices.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<TutorResponseDTO>> listarTodos() {
        return ResponseEntity.ok(tutorServices.listarTodos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        tutorServices.deletarTutor(id);
        return ResponseEntity.noContent().build();
    }

}
