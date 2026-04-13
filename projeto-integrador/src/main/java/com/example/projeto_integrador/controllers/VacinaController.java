package com.example.projeto_integrador.controllers;

import com.example.projeto_integrador.dtos.VacinaRequestDTO;
import com.example.projeto_integrador.dtos.VacinaResponseDTO;
import com.example.projeto_integrador.services.VacinaServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vacinas")
@RequiredArgsConstructor
public class VacinaController {

    private final VacinaServices vacinaServices;

    @PostMapping
    public ResponseEntity<VacinaResponseDTO> cadastrar(@RequestBody @Valid VacinaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vacinaServices.cadastrar(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VacinaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(vacinaServices.buscarPorId(id));
    }

    @GetMapping("/prontuario/{prontuarioId}")
    public ResponseEntity<List<VacinaResponseDTO>> listarPorProntuario(@PathVariable Long prontuarioId) {
        return ResponseEntity.ok(vacinaServices.listarPorProntuario(prontuarioId));
    }

    @GetMapping("/atrasadas")
    public ResponseEntity<List<VacinaResponseDTO>> listarAtrasadas() {
        return ResponseEntity.ok(vacinaServices.listarAtrasadas());
    }
}