package com.infnet.extintor_service.messaging;


import com.infnet.extintor_service.model.Extintor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitMQSender {

    private final RabbitTemplate rabbitTemplate;

    public void enviarExtintorParaHistorico(Extintor extintor) {
        log.info("Enviando extintor para o histórico via RabbitMQ: {}", extintor.getNumeroControleInterno());
        rabbitTemplate.convertAndSend("extintor-exchange", "extintor.historico", extintor);
    }
}
