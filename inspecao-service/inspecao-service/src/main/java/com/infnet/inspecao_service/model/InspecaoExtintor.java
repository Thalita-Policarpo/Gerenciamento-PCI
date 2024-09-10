package com.infnet.inspecao_service.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "inspecoes")
public class InspecaoExtintor {
    @Id
    private String id;
    private LocalDate dataInspecao;
    private boolean sinalizado;
    private boolean desobstruido;
    private boolean manometroPressaoAdequada;
    private boolean gatilhoBoasCondicoes;
    private boolean mangoteBoasCondicoes;
    private boolean rotuloPinturaBoasCondicoes;
    private boolean suporteBoasCondicoes;
    private boolean lacreIntacto;
    private String status; // Conforme ou Não conforme
    private String extintorId;  // Referência ao extintor inspecionado
}
