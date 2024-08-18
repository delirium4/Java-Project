package org.lab.ownerMicroservice.Subscriber;

import lombok.AllArgsConstructor;
import org.lab.dao.models.Owner;
import org.lab.dao.responseModels.OwnerSaveModel;
import org.lab.ownerMicroservice.PersistRequest.PersistHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.lab.ownerMicroservice.Service.OwnerService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class OwnerConsumer {
    private final OwnerService ownerService;

    @RabbitListener(queues = "${rabbitmq.json.queue.name}")
    public void consume(OwnerSaveModel owner){
        new PersistHandler(ownerService).Execute(owner.castOwnerSaveModelToOwner());
    }
}
