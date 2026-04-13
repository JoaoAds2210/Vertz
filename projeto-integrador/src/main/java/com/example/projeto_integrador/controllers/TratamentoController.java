package com.example.projeto_integrador.controllers;

import com.example.projeto_integrador.dtos.TratamentoRequestDTO;
import com.example.projeto_integrador.dtos.TratamentoResponseDTO;
import com.example.projeto_integrador.services.TratamentoServices;
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
@RequestMapping("/tratamentos")
@RequiredArgsConstructor
@Tag(name = "Tratamentos", description = "Gerenciamento de tratamentos")
public class TratamentoController {

    private final TratamentoServices tratamentoServices;

    @Operation(summary = "Cadastrar tratamento")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Tratamento cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Prontuário não encontrado"),
            @ApiResponse(responseCode = "422", description = "Data fim anterior à data início")
    })
    @PostMapping
    public ResponseEntity<TratamentoResponseDTO> cadastrar(@RequestBody @Valid TratamentoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tratamentoServices.cadastrar(dto));
    }

    @Operation(summary = "Buscar tratamento por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tratamento encontrado"),
            @ApiResponse(responseCode = "404", description = "Tratamento não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TratamentoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(tratamentoServices.buscarPorId(id));
    }

    @Operation(summary = "Listar tratamentos por prontuário")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping("/prontuario/{prontuarioId}")
    public ResponseEntity<List<TratamentoResponseDTO>> listarPorProntuario(@PathVariable Long prontuarioId) {
        return ResponseEntity.ok(tratamentoServices.listarPorProntuario(prontuarioId));
    }

    @Operation(summary = "Listar tratamentos ativos por prontuário")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping("/prontuario/{prontuarioId}/ativos")
    public ResponseEntity<List<TratamentoResponseDTO>> listarAtivos(@PathVariable Long prontuarioId) {
        return ResponseEntity.ok(tratamentoServices.listarAtivosPorProntuario(prontuarioId));
    }

    @Operation(summary = "Finalizar tratamento")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Tratamento finalizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tratamento não encontrado"),
            @ApiResponse(responseCode = "422", description = "Tratamento já finalizado ou cancelado")
    })
    @PatchMapping("/{id}/finalizar")
    public ResponseEntity<Void> finalizar(@PathVariable Long id) {
        tratamentoServices.finalizarTratamento(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Cancelar tratamento")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Tratamento cancelado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tratamento não encontrado"),
            @ApiResponse(responseCode = "422", description = "Tratamento já finalizado ou cancelado")
    })
    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        tratamentoServices.cancelarTratamento(id);
        return ResponseEntity.noContent().build();
    }
}