package com.infnet.inspecao_service.controller;


import com.infnet.inspecao_service.model.InspecaoExtintor;
import com.infnet.inspecao_service.service.InspecaoExtintorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<List<InspecaoExtintor>> getAllInspecoes() {
        List<InspecaoExtintor> inspecoes = inspecaoExtintorService.findAllInspecoes();
        if (inspecoes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(inspecoes);
    }

    @Operation(summary = "Buscar inspeção por ID", description = "Busca uma inspeção específica pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inspeção encontrada"),
            @ApiResponse(responseCode = "404", description = "Inspeção não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<InspecaoExtintor> getInspecaoById(@PathVariable String id) {
        return inspecaoExtintorService.findInspecaoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Buscar inspeções por ID do extintor", description = "Busca todas as inspeções feitas em um extintor específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inspeções encontradas"),
            @ApiResponse(responseCode = "404", description = "Nenhuma inspeção encontrada")
    })
    @GetMapping("/extintor/{extintorId}")
    public ResponseEntity<List<InspecaoExtintor>> getInspecoesByExtintorId(@PathVariable String extintorId) {
        List<InspecaoExtintor> inspecoes = inspecaoExtintorService.findInspecoesByExtintorId(extintorId);
        if (inspecoes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(inspecoes);
    }

    @Operation(summary = "Cadastrar uma nova inspeção", description = "Cadastra uma nova inspeção para um extintor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Inspeção cadastrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao cadastrar inspeção")
    })
    @PostMapping
    public ResponseEntity<InspecaoExtintor> createInspecao(@RequestBody InspecaoExtintor inspecao) {
        InspecaoExtintor savedInspecao = inspecaoExtintorService.saveInspecao(inspecao);
        return ResponseEntity.status(201).body(savedInspecao);
    }

    @Operation(summary = "Deletar uma inspeção", description = "Remove uma inspeção do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Inspeção deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Inspeção não encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInspecao(@PathVariable String id) {
        if (inspecaoExtintorService.findInspecaoById(id).isPresent()) {
            inspecaoExtintorService.deleteInspecao(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
