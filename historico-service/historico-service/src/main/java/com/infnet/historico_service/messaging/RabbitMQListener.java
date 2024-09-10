package com.infnet.historico_service.messaging;

import com.infnet.historico_service.model.HistoricoExtintor;
import com.infnet.historico_service.repository.HistoricoExtintorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitMQListener {

    private final HistoricoExtintorRepository historicoExtintorRepository;

    @RabbitListener(queues = "historico-queue")
    public void receberExtintor(HistoricoExtintor historicoExtintor) {
        log.info("Recebendo mensagem do RabbitMQ para salvar histórico: {}", historicoExtintor.getNumeroControleInterno());
        historicoExtintorRepository.save(historicoExtintor);
    }
}
