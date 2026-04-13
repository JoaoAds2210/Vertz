package com.example.projeto_integrador.controllers;

import com.example.projeto_integrador.dtos.TutorRequestDTO;
import com.example.projeto_integrador.dtos.TutorResponseDTO;
import com.example.projeto_integrador.services.TutorServices;
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
@RequestMapping("/tutores")
@RequiredArgsConstructor
@Tag(name = "Tutores", description = "Gerenciamento de tutores")
public class TutorController {

    private final TutorServices tutorServices;

    @Operation(summary = "Cadastrar tutor")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Tutor cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "409", description = "E-mail já cadastrado")
    })
    @PostMapping
    public ResponseEntity<TutorResponseDTO> cadastrar(@RequestBody @Valid TutorRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tutorServices.cadastrar(dto));
    }

    @Operation(summary = "Buscar tutor por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tutor encontrado"),
            @ApiResponse(responseCode = "404", description = "Tutor não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TutorResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(tutorServices.buscarPorId(id));
    }

    @Operation(summary = "Listar todos os tutores")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    })
    @GetMapping
    public ResponseEntity<List<TutorResponseDTO>> listarTodos() {
        return ResponseEntity.ok(tutorServices.listarTodos());
    }

    @Operation(summary = "Deletar tutor")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Tutor deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tutor não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        tutorServices.deletarTutor(id);
        return ResponseEntity.noContent().build();
    }

}
