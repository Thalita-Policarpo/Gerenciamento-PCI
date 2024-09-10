package com.infnet.extintor_service.service;


import com.infnet.extintor_service.messaging.RabbitMQSender;
import com.infnet.extintor_service.model.Extintor;
import com.infnet.extintor_service.dto.ExtintorDTO;
import com.infnet.extintor_service.repository.ExtintorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class ExtintorService {

    private final ExtintorRepository extintorRepository;
    private final RabbitMQSender rabbitMQSender;

    public List<Extintor> findAllExtintores() {
        log.info("Buscando todos os extintores");
        List<Extintor> extintores = extintorRepository.findAll();
        if (extintores.isEmpty()) {
            throw new IllegalArgumentException("Não existe nenhum extintor cadastrado ainda");
        }
        return extintorRepository.findAll();
    }

    public Optional<Extintor> findExtintorById(String id) {
        log.info("Buscando extintor pelo ID: {}", id);
        Extintor extintor = extintorRepository.findById(id).orElse(null);
        if (extintor == null) {
            throw new IllegalArgumentException("Id inválido: " + id);
        }
        return extintorRepository.findById(id);
    }

    public Optional<Extintor> findExtintorByNumeroControleInterno(int numeroControleInterno) {
        log.info("Buscando extintor pelo número de controle interno: {}", numeroControleInterno);
        Extintor extintor = extintorRepository.findByNumeroControleInterno(numeroControleInterno).orElse(null);
        if (extintor == null) {
            throw new IllegalArgumentException("Id inválido: " + numeroControleInterno);
        }
        return extintorRepository.findByNumeroControleInterno(numeroControleInterno);
    }

    public Extintor saveExtintorFromDTO(ExtintorDTO extintorDTO) {
        if (extintorRepository.findByNumeroControleInterno(extintorDTO.getNumeroControleInterno()).isPresent()) {
            log.info("Extintor com este número de controle interno já existe");
            throw new IllegalArgumentException("Extintor com este número de controle interno já existe");
        }

        // Converte ExtintorDTO para Extintor
        Extintor extintor = new Extintor();
        extintor.setNumeroControleInterno(extintorDTO.getNumeroControleInterno());
        extintor.setNumeroCilindro(extintorDTO.getNumeroCilindro());
        extintor.setNumeroSeloInmetro(extintorDTO.getNumeroSeloInmetro());
        extintor.setCargaExtintora(extintorDTO.getCargaExtintora());
        extintor.setCapacidade(extintorDTO.getCapacidade());
        extintor.setDataVencimento(extintorDTO.getDataVencimento());
        extintor.setProximoTesteHidrostatico(extintorDTO.getProximoTesteHidrostatico());

        // Salva o extintor e obtém a instância salva
        Extintor extintorSalvo = extintorRepository.save(extintor);

        log.info("Enviar extintor para Histórico");
        // Envia a mensagem para o RabbitMQ com o extintor salvo
        rabbitMQSender.enviarExtintorParaHistorico(extintorSalvo);

        log.info("Extintor cadastrado com sucesso!");
        return extintorSalvo;
    }

    public void deleteExtintor(String id) {
        if(findExtintorById(id).isPresent()){
            log.info("Excluindo extintor com ID: {}", id);
            extintorRepository.deleteById(id);
        }else {
            throw new IllegalArgumentException("Id inválido: " + id);
        }
    }
}
