package com.infnet.historico_service.controller;

import com.infnet.historico_service.model.HistoricoExtintor;
import com.infnet.historico_service.service.HistoricoExtintorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/historicos")
@RequiredArgsConstructor
public class HistoricoExtintorController {

    private final HistoricoExtintorService historicoExtintorService;

    @Operation(summary = "Buscar todo o histórico", description = "Retorna uma lista com todo o histórico de modificações dos extintores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Histórico encontrado"),
            @ApiResponse(responseCode = "404", description = "Nenhum histórico encontrado")
    })
    @GetMapping
    public ResponseEntity<List<HistoricoExtintor>> getAllHistoricos() {
        List<HistoricoExtintor> historicos = historicoExtintorService.findAllHistoricos();
        if (historicos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(historicos);
    }

    @Operation(summary = "Buscar histórico por ID", description = "Busca o histórico de modificações de um extintor pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Histórico encontrado"),
            @ApiResponse(responseCode = "404", description = "Histórico não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<HistoricoExtintor> getHistoricoById(@PathVariable String id) {
        return historicoExtintorService.findHistoricoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Buscar histórico pelo número de controle interno", description = "Busca o histórico de modificações de um extintor pelo número de controle interno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Histórico encontrado"),
            @ApiResponse(responseCode = "404", description = "Histórico não encontrado")
    })
    @GetMapping("/numeroControleInterno/{numeroControleInterno}")
    public ResponseEntity<List<HistoricoExtintor>> getHistoricoByNumeroControleInterno(@PathVariable int numeroControleInterno) {
        List<HistoricoExtintor> historicos = historicoExtintorService.findHistoricoByNumeroControleInterno(numeroControleInterno);
        if (historicos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(historicos);
    }
}
