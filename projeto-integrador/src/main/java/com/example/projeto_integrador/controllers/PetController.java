package com.example.projeto_integrador.controllers;

import com.example.projeto_integrador.dtos.PetRequestDTO;
import com.example.projeto_integrador.dtos.PetResponseDTO;
import com.example.projeto_integrador.services.PetServices;
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
@RequestMapping("/pets")
@RequiredArgsConstructor
@Tag(name = "Pets", description = "Gerenciamento de pets")
public class PetController {

    private final PetServices petServices;

    @Operation(summary = "Cadastrar pet", description = "Cria o pet e seu prontuário automaticamente")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Pet cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Tutor não encontrado")
    })
    @PostMapping
    public ResponseEntity<PetResponseDTO> cadastrar(@RequestBody @Valid PetRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(petServices.cadastrar(dto));
    }

    @Operation(summary = "Buscar pet por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pet encontrado"),
            @ApiResponse(responseCode = "404", description = "Pet não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PetResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(petServices.buscarPorId(id));
    }

    @Operation(summary = "Listar pets por tutor")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tutor não encontrado")
    })
    @GetMapping("/tutor/{tutorId}")
    public ResponseEntity<List<PetResponseDTO>> listarPorTutor(@PathVariable Long tutorId) {
        return ResponseEntity.ok(petServices.listarPorTutor(tutorId));
    }

    @Operation(summary = "Deletar pet")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Pet deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pet não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        petServices.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
