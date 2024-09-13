package com.infnet.inspecao_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InspecaoExtintorDTO {

    private boolean sinalizado;
    private boolean desobstruido;
    private boolean manometroPressaoAdequada;
    private boolean gatilhoBoasCondicoes;
    private boolean mangoteBoasCondicoes;
    private boolean rotuloPinturaBoasCondicoes;
    private boolean suporteBoasCondicoes;
    private boolean lacreIntacto;


}