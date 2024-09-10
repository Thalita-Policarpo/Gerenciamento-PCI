package com.infnet.inspecao_service.service;

import com.infnet.inspecao_service.model.InspecaoExtintor;
import com.infnet.inspecao_service.repository.InspecaoExtintorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class InspecaoExtintorService {

    private final InspecaoExtintorRepository inspecaoExtintorRepository;
    private final RestTemplate restTemplate; // Comunicação com Extintor-service

    public List<InspecaoExtintor> findAllInspecoes() {
        log.info("Buscando todas as inspeções");
        return inspecaoExtintorRepository.findAll();
    }

    public Optional<InspecaoExtintor> findInspecaoById(String id) {
        log.info("Buscando inspeção pelo ID: {}", id);
        return inspecaoExtintorRepository.findById(id);
    }

    public List<InspecaoExtintor> findInspecoesByExtintorId(String extintorId) {
        log.info("Buscando inspeções pelo ID do extintor: {}", extintorId);
        return inspecaoExtintorRepository.findByExtintorId(extintorId);
    }

    public InspecaoExtintor saveInspecao(InspecaoExtintor inspecaoExtintor) {
        log.info("Salvando nova inspeção para extintor com ID: {}", inspecaoExtintor.getExtintorId());

        // Validação: Verificar se o extintor está cadastrado no Extintor-service
        String url = "http://extintor-service/extintores/" + inspecaoExtintor.getExtintorId();
        Boolean extintorExiste = restTemplate.getForObject(url, Boolean.class);

        if (!Boolean.TRUE.equals(extintorExiste)) {
            log.error("Extintor não cadastrado!");
            throw new IllegalStateException("Extintor não cadastrado!");
        }

        // Verificar validade da inspeção
        if (isValidInspecao(inspecaoExtintor)) {
            inspecaoExtintor.setStatus("Conforme");
        } else {
            inspecaoExtintor.setStatus("Não conforme");
        }

        return inspecaoExtintorRepository.save(inspecaoExtintor);
    }

    private boolean isValidInspecao(InspecaoExtintor inspecao) {
        // Verifica se todos os campos booleanos são verdadeiros e se as datas são válidas
        boolean camposValidos = inspecao.isSinalizado() && inspecao.isDesobstruido()
                && inspecao.isManometroPressaoAdequada() && inspecao.isGatilhoBoasCondicoes()
                && inspecao.isMangoteBoasCondicoes() && inspecao.isRotuloPinturaBoasCondicoes()
                && inspecao.isSuporteBoasCondicoes() && inspecao.isLacreIntacto();

        LocalDate hoje = LocalDate.now();
        boolean validadeExtintorValida = hoje.isBefore(inspecao.getDataInspecao()); // Simulação

        return camposValidos && validadeExtintorValida;
    }

    public void deleteInspecao(String id) {
        log.info("Deletando inspeção com ID: {}", id);
        inspecaoExtintorRepository.deleteById(id);
    }
}
