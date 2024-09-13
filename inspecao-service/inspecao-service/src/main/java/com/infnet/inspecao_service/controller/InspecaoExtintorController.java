package com.infnet.inspecao_service.controller;

import com.infnet.inspecao_service.model.InspecaoExtintor;
import com.infnet.inspecao_service.service.InspecaoExtintorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inspecoes")
@RequiredArgsConstructor
public class InspecaoExtintorController {

    private final InspecaoExtintorService inspecaoExtintorService;

    @Operation(summary = "Buscar todas as inspeções", description = "Retorna uma lista de todas as inspeções cadastradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inspeções encontradas"),
            @ApiResponse(responseCode = "404", description = "Nenhuma inspeção encontrada")
    })
    @GetMapping
    public ResponseEntity<?> getAllInspecoes() {
        try {
            List<InspecaoExtintor> inspecoes = inspecaoExtintorService.findAllInspecoes();
            return ResponseEntity.ok(inspecoes);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Buscar inspeção por ID", description = "Busca uma inspeção específica pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inspeção encontrada"),
            @ApiResponse(responseCode = "404", description = "Inspeção não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getInspecaoById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(inspecaoExtintorService.findInspecaoById(id));
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Buscar inspeções por ID do extintor", description = "Busca todas as inspeções feitas em um extintor específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inspeções encontradas"),
            @ApiResponse(responseCode = "404", description = "Nenhuma inspeção encontrada")
    })
    @GetMapping("/verificar/{extintorId}")
    public ResponseEntity<?> getInspecoesByExtintorId(@PathVariable String extintorId) {
        try {
            List<InspecaoExtintor> inspecoes = inspecaoExtintorService.findInspecoesByExtintorId(extintorId);
            return ResponseEntity.ok(inspecoes);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Cadastrar uma nova inspeção", description = "Cadastra uma nova inspeção para um extintor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Inspeção cadastrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao cadastrar inspeção")
    })
    @PostMapping
    public ResponseEntity<?> createInspecao(@RequestBody InspecaoExtintor inspecao) {
        try {
            inspecaoExtintorService.saveInspecao(inspecao);
            return new ResponseEntity<>("Inspeção incluída com sucesso!", HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Deletar uma inspeção", description = "Remove uma inspeção do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Inspeção deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Inspeção não encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInspecao(@PathVariable String id) {
        try {
            inspecaoExtintorService.deleteInspecao(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
