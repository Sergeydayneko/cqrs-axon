package ru.dayneko.config;

import lombok.Data;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@Data
public class AMQPConfig {

    @Value("${broker.routes.base}")
    private String baseRoute;

    @Value("${broker.queues.base}")
    private String baseQueue;

    @Value("${broker.fanout.autodelete}")
    private boolean autodelete;

    private final RabbitProperties rabbitProperties;

    @PostConstruct
    public void dsd() {
        System.out.println(baseRoute + " " + baseQueue + " " + autodelete);
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

    @RabbitListener(queues = "common_queue")
    public void listTest(String message) {
        System.out.println(" I GOUT MSG " + message);

    }
}
