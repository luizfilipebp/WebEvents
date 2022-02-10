package com.microservice.connection;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RabbitMqConnection {
    private static final String NAME_EXCHANGE  = "amq.direct";
    public static final String FILA_USER = "USER";
    public static final String FILA_EVENT = "EVENT";
    private AmqpAdmin amqpAdmin;

    public RabbitMqConnection(AmqpAdmin amqpAdmin){
        this.amqpAdmin = amqpAdmin;
    }

    private Queue queue (String nameQueue){
        return new Queue(nameQueue, true, false,false);
    }

    private DirectExchange exchange(){
        return new DirectExchange(NAME_EXCHANGE);
    }

    private Binding binding(Queue queue, DirectExchange exchange){
      return new Binding(queue.getName(), Binding.DestinationType.QUEUE, exchange.getName(), queue.getName(), null);
    }

    @PostConstruct
    private void add(){
        Queue queueUser = this.queue(FILA_USER);
        Queue queueEvent = this.queue(FILA_EVENT);

        DirectExchange exchange = this.exchange();

        Binding relationUser = this.binding(queueUser, exchange);
        Binding relationEvent = this.binding(queueEvent, exchange);

        this.amqpAdmin.declareQueue(queueEvent);
        this.amqpAdmin.declareQueue(queueUser);

        this.amqpAdmin.declareExchange(exchange);

        this.amqpAdmin.declareBinding(relationUser);
        this.amqpAdmin.declareBinding(relationEvent);
    }



}
