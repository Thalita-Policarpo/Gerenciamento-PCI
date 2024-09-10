package com.infnet.historico_service.service;

import com.infnet.historico_service.model.HistoricoExtintor;
import com.infnet.historico_service.repository.HistoricoExtintorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class HistoricoExtintorService {

    private final HistoricoExtintorRepository historicoExtintorRepository;

    public List<HistoricoExtintor> findAllHistoricos() {
        log.info("Buscando todo o histórico de extintores");
        return historicoExtintorRepository.findAll();
    }

    public Optional<HistoricoExtintor> findHistoricoById(String id) {
        log.info("Buscando histórico pelo ID: {}", id);
        return historicoExtintorRepository.findById(id);
    }

    public List<HistoricoExtintor> findHistoricoByNumeroControleInterno(int numeroControleInterno) {
        log.info("Buscando histórico pelo número de controle interno: {}", numeroControleInterno);
        return historicoExtintorRepository.findByNumeroControleInterno(numeroControleInterno);
    }

    public HistoricoExtintor saveHistorico(HistoricoExtintor historicoExtintor) {
        log.info("Salvando histórico do extintor com número de controle interno: {}", historicoExtintor.getNumeroControleInterno());
        historicoExtintor.setDataModificacao(LocalDateTime.now());
        return historicoExtintorRepository.save(historicoExtintor);
    }
}
