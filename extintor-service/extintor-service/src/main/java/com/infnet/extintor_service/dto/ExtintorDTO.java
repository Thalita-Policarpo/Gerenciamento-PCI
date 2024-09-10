package com.infnet.extintor_service.dto;

import lombok.Data;

import java.time.Year;
import java.time.YearMonth;

@Data
public class ExtintorDTO {
    private int numeroControleInterno;
    private String numeroCilindro;
    private String numeroSeloInmetro;
    private String cargaExtintora;
    private String capacidade;
    private YearMonth dataVencimento;
    private Year proximoTesteHidrostatico;
}