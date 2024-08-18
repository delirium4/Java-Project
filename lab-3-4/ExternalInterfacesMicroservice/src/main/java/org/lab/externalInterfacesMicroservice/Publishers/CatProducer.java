package org.lab.externalInterfacesMicroservice.Publishers;

import org.lab.dao.responseModels.CatSaveModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CatProducer {
    @Value("${rabbitmq.cat.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.cat.json.routing.key}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;

    public CatProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendCat(CatSaveModel cat){
        rabbitTemplate.convertAndSend(exchange, routingKey, cat);
    }
}
