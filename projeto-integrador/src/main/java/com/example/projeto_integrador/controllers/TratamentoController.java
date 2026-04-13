package com.example.projeto_integrador.controllers;

import com.example.projeto_integrador.dtos.TratamentoRequestDTO;
import com.example.projeto_integrador.dtos.TratamentoResponseDTO;
import com.example.projeto_integrador.services.TratamentoServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tratamentos")
@RequiredArgsConstructor
public class TratamentoController {

    private final TratamentoServices tratamentoServices;

    @PostMapping
    public ResponseEntity<TratamentoResponseDTO> cadastrar(@RequestBody @Valid TratamentoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tratamentoServices.cadastrar(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TratamentoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(tratamentoServices.buscarPorId(id));
    }

    @GetMapping("/prontuario/{prontuarioId}")
    public ResponseEntity<List<TratamentoResponseDTO>> listarPorProntuario(@PathVariable Long prontuarioId) {
        return ResponseEntity.ok(tratamentoServices.listarPorProntuario(prontuarioId));
    }

    @GetMapping("/prontuario/{prontuarioId}/ativos")
    public ResponseEntity<List<TratamentoResponseDTO>> listarAtivos(@PathVariable Long prontuarioId) {
        return ResponseEntity.ok(tratamentoServices.listarAtivosPorProntuario(prontuarioId));
    }

    @PatchMapping("/{id}/finalizar")
    public ResponseEntity<Void> finalizar(@PathVariable Long id) {
        tratamentoServices.finalizarTratamento(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        tratamentoServices.cancelarTratamento(id);
        return ResponseEntity.noContent().build();
    }
}