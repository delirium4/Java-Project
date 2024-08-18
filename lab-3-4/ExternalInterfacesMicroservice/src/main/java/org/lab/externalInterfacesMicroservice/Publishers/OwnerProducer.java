package org.lab.externalInterfacesMicroservice.Publishers;

import org.lab.dao.models.Owner;
import org.lab.dao.responseModels.OwnerSaveModel;
import org.lab.externalInterfacesMicroservice.Util.MessageUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OwnerProducer {
    @Value("${rabbitmq.owner.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.owner.json.routing.key}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;

    public OwnerProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendOwner(OwnerSaveModel owner){
        rabbitTemplate.convertAndSend(exchange, routingKey, owner);
    }
}
