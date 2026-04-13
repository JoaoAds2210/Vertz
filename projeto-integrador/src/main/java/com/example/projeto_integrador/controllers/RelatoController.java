package com.example.projeto_integrador.controllers;

import com.example.projeto_integrador.dtos.RelatoRequestDTO;
import com.example.projeto_integrador.dtos.RelatoResponseDTO;
import com.example.projeto_integrador.model.StatusRelato;
import com.example.projeto_integrador.services.RelatoServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/relatos")
@RequiredArgsConstructor
public class RelatoController {

    private final RelatoServices relatoServices;

    @PostMapping
    public ResponseEntity<RelatoResponseDTO> cadastrar(@RequestBody @Valid RelatoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(relatoServices.cadastrar(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RelatoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(relatoServices.buscarPorId(id));
    }

    @GetMapping("/prontuario/{prontuarioId}")
    public ResponseEntity<List<RelatoResponseDTO>> listarPorStatus(
            @PathVariable Long prontuarioId,
            @RequestParam StatusRelato status) {
        return ResponseEntity.ok(relatoServices.listarPorStatus(prontuarioId, status));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<RelatoResponseDTO> atualizarStatus(
            @PathVariable Long id,
            @RequestParam StatusRelato status) {
        return ResponseEntity.ok(relatoServices.atualizarStatus(id, status));
    }
}