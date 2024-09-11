package com.infnet.extintor_service.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Year;
import java.time.YearMonth;
import java.util.List;

@Data
@Document(collection = "extintores")
public class Extintor {
    @Id
    private String id;
    private int numeroControleInterno;
    private String numeroCilindro;
    private String numeroSeloInmetro;
    private String cargaExtintora;
    private String capacidade;
    //private YearMonth dataVencimento;
    //private Year proximoTesteHidrostatico;
    private List<String> inspecoesId;  // Referência para as inspeções associadas
}
