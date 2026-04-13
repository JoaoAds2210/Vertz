package com.example.projeto_integrador.controllers;

import com.example.projeto_integrador.dtos.RelatoRequestDTO;
import com.example.projeto_integrador.dtos.RelatoResponseDTO;
import com.example.projeto_integrador.model.StatusRelato;
import com.example.projeto_integrador.services.RelatoServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/relatos")
@RequiredArgsConstructor
@Tag(name = "Relatos", description = "Gerenciamento de relatos de saúde")
public class RelatoController {

    private final RelatoServices relatoServices;

    @Operation(summary = "Cadastrar relato")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Relato cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Prontuário não encontrado")
    })
    @PostMapping
    public ResponseEntity<RelatoResponseDTO> cadastrar(@RequestBody @Valid RelatoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(relatoServices.cadastrar(dto));
    }

    @Operation(summary = "Buscar relato por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Relato encontrado"),
            @ApiResponse(responseCode = "404", description = "Relato não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<RelatoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(relatoServices.buscarPorId(id));
    }

    @Operation(summary = "Listar relatos por status", description = "Filtra relatos de um prontuário pelo status: ABERTO, EM_ANALISE ou FINALIZADO")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping("/prontuario/{prontuarioId}")
    public ResponseEntity<List<RelatoResponseDTO>> listarPorStatus(
            @PathVariable Long prontuarioId,
            @RequestParam StatusRelato status) {
        return ResponseEntity.ok(relatoServices.listarPorStatus(prontuarioId, status));
    }

    @Operation(summary = "Atualizar status do relato")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Status atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Relato não encontrado")
    })
    @PatchMapping("/{id}/status")
    public ResponseEntity<RelatoResponseDTO> atualizarStatus(
            @PathVariable Long id,
            @RequestParam StatusRelato status) {
        return ResponseEntity.ok(relatoServices.atualizarStatus(id, status));
    }
}