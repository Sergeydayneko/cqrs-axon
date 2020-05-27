package ru.dayneko.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AMQPConfig {

    @Value("${broker.routes.base}")
    private String baseRoute;

    @Value("${broker.queues.base}")
    private String baseQueue;

    @Value("${broker.fanout.autodelete}")
    private boolean autodelete;

    private final RabbitProperties rabbitProperties;

    public AMQPConfig(RabbitProperties rabbitProperties) {
        this.rabbitProperties = rabbitProperties;
    }

    @Bean
    Queue queue() {
        return new Queue(baseQueue, true);
    }

    @Bean
    Exchange exchange() {
        ExchangeBuilder builder = ExchangeBuilder
                .fanoutExchange("mock")
                .durable(true);
        if (autodelete) {
            return builder.autoDelete().build();
        }
        return builder.build();
    }

    @Bean
    Binding binding(Queue queue, Exchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(baseRoute)
                .noargs();
    }
}
