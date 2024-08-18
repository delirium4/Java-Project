package org.lab.externalInterfacesMicroservice.RabbitMQConfig;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class RabbitMQConfig {

    @Value("${rabbitmq.cat.exchange.name}")
    private String catExchange;

    @Value("${rabbitmq.cat.json.queue.name}")
    private String catJSONQueue;

    @Value("${rabbitmq.cat.json.routing.key}")
    private String catJSONRoutingKey;

    @Value("${rabbitmq.owner.exchange.name}")
    private String ownerExchange;

    @Value("${rabbitmq.owner.json.queue.name}")
    private String ownerJSONQueue;

    @Value("${rabbitmq.owner.json.routing.key}")
    private String ownerJSONRoutingKey;

    @Bean
    public TopicExchange ownerTopicExchange(){
        return new TopicExchange(ownerExchange);
    }

    @Bean
    public Queue jsonOwnerQueue(){
        return new Queue(ownerJSONQueue);
    }

    @Bean
    public Binding jsonOwnerQueueBinding(){
        return BindingBuilder.bind(jsonOwnerQueue()).to(ownerTopicExchange()).with(ownerJSONRoutingKey);
    }

    @Bean
    public TopicExchange catTopicExchange(){
        return new TopicExchange(catExchange);
    }

    @Bean
    public Queue jsonCatQueue(){
        return new Queue(catJSONQueue);
    }

    @Bean
    public Binding jsonCatBinding(){
        return BindingBuilder.bind(jsonCatQueue()).to(catTopicExchange()).with(catJSONRoutingKey);
    }

    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
