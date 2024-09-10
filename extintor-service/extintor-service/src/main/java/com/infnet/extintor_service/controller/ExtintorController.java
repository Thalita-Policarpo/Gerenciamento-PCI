package com.infnet.extintor_service.controller;

import com.infnet.extintor_service.dto.ExtintorDTO;
import com.infnet.extintor_service.model.Extintor;
import com.infnet.extintor_service.service.ExtintorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/extintores")
@RequiredArgsConstructor
public class ExtintorController {

    private final ExtintorService extintorService;

    @Operation(summary = "Buscar todos os extintores", description = "Retorna uma lista de todos os extintores cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Extintores encontrados"),
            @ApiResponse(responseCode = "404", description = "Nenhum extintor encontrado")
    })
    @GetMapping
    public ResponseEntity<?> getAllExtintores() {
        try {
            List<Extintor> extintores = extintorService.findAllExtintores();
            return ResponseEntity.ok(extintores);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @Operation(summary = "Buscar extintor por ID", description = "Busca um extintor específico pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Extintor encontrado"),
            @ApiResponse(responseCode = "404", description = "Extintor não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getExtintorById(@PathVariable String id) {
        try {
            return extintorService.findExtintorById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @Operation(summary = "Buscar extintor por número de controle interno", description = "Busca um extintor específico pelo número de controle interno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Extintor encontrado"),
            @ApiResponse(responseCode = "404", description = "Extintor não encontrado")
    })
    @GetMapping("/controle-interno/{numeroControleInterno}")
    public ResponseEntity<?> getExtintorByNumeroControleInterno(@PathVariable int numeroControleInterno) {
        try {
            return extintorService.findExtintorByNumeroControleInterno(numeroControleInterno)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Cadastrar um novo extintor", description = "Cadastra um novo extintor no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Extintor cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao cadastrar extintor")
    })
    @PostMapping
    public ResponseEntity<?> createExtintor(@RequestBody ExtintorDTO extintorDTO) {
        try {
            Extintor savedExtintor = extintorService.saveExtintorFromDTO(extintorDTO);
            return ResponseEntity.status(201).body(savedExtintor);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @Operation(summary = "Deletar um extintor", description = "Remove um extintor do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Extintor deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Extintor não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExtintor(@PathVariable String id) {
        try {
            extintorService.deleteExtintor(id);
            ResponseEntity.ok("Extintor deletado com sucesso");

        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
        return null;
    }
}