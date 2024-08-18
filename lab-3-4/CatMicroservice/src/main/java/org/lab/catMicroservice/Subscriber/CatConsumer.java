package org.lab.catMicroservice.Subscriber;


import lombok.AllArgsConstructor;
import org.lab.catMicroservice.PersistRequestHandler.PersistHandler;
import org.lab.catMicroservice.Service.CatService;
import org.lab.dao.responseModels.CatSaveModel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CatConsumer {

    private final CatService catService;

    @RabbitListener(queues = "${rabbitmq.json.queue.name}")
    public void consume(CatSaveModel catSaveModel){
        new PersistHandler(catService).Execute(catSaveModel);
    }
}
