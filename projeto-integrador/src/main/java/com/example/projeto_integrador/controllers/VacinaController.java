package com.example.projeto_integrador.controllers;

import com.example.projeto_integrador.dtos.VacinaRequestDTO;
import com.example.projeto_integrador.dtos.VacinaResponseDTO;
import com.example.projeto_integrador.services.VacinaServices;
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
@RequestMapping("/vacinas")
@RequiredArgsConstructor
@Tag(name = "Vacinas", description = "Gerenciamento de vacinas")
public class VacinaController {

    private final VacinaServices vacinaServices;

    @Operation(summary = "Cadastrar vacina")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Vacina cadastrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Prontuário não encontrado"),
            @ApiResponse(responseCode = "422", description = "Data da próxima dose anterior à data de aplicação")
    })
    @PostMapping
    public ResponseEntity<VacinaResponseDTO> cadastrar(@RequestBody @Valid VacinaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vacinaServices.cadastrar(dto));
    }

    @Operation(summary = "Buscar vacina por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vacina encontrada"),
            @ApiResponse(responseCode = "404", description = "Vacina não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<VacinaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(vacinaServices.buscarPorId(id));
    }

    @Operation(summary = "Listar vacinas por prontuário")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping("/prontuario/{prontuarioId}")
    public ResponseEntity<List<VacinaResponseDTO>> listarPorProntuario(@PathVariable Long prontuarioId) {
        return ResponseEntity.ok(vacinaServices.listarPorProntuario(prontuarioId));
    }

    @Operation(summary = "Listar vacinas atrasadas", description = "Retorna todas as vacinas com próxima dose vencida")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    @GetMapping("/atrasadas")
    public ResponseEntity<List<VacinaResponseDTO>> listarAtrasadas() {
        return ResponseEntity.ok(vacinaServices.listarAtrasadas());
    }
}