package com.infnet.historico_service.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.Year;
import java.time.YearMonth;

@Data
@Document(collection = "historicos")
public class HistoricoExtintor {
    @Id
    private String id;
    private int numeroControleInterno;
    private String numeroCilindro;
    private String numeroSeloInmetro;
    private String cargaExtintora;
    private String capacidade;
    private YearMonth dataVencimento;
    private Year proximoTesteHidrostatico;
    private LocalDateTime dataModificacao;
    private String usuarioModificacao;
}