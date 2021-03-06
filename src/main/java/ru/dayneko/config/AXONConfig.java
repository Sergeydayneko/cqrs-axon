package ru.dayneko.config;

import com.rabbitmq.client.Channel;

import org.axonframework.amqp.eventhandling.AMQPMessageConverter;
import org.axonframework.amqp.eventhandling.DefaultAMQPMessageConverter;
import org.axonframework.amqp.eventhandling.spring.SpringAMQPMessageSource;
import org.axonframework.config.EventHandlingConfiguration;
import org.axonframework.serialization.Serializer;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * AXON Config for AMQP exchanging
 */
@Configuration
public class AXONConfig {
    
    /* Settings for message handling order */
    @Bean
    @Scope(value = "prototype")
    public AMQPMessageConverter amqpMessageConverter(Serializer serializer) {
        return new DefaultAMQPMessageConverter(serializer);
    }

    @Autowired
    public void configure(EventHandlingConfiguration config) {
        config.usingTrackingProcessors();
    }

    @Autowired
    public void configure(EventHandlingConfiguration eventHandlingConfiguration, SpringAMQPMessageSource springAMQPMessageSource) {
        eventHandlingConfiguration.registerSubscribingEventProcessor("accountProcessingGroup", conf -> springAMQPMessageSource);
    }

    @Bean
    public SpringAMQPMessageSource springAMQPMessageSource(AMQPMessageConverter amqpMessageConverter) {
        return new SpringAMQPMessageSource(amqpMessageConverter) {
            @Override
            @RabbitListener(queues = "common_queue")
            public void onMessage(Message message, Channel channel) throws Exception {
                super.onMessage(message, channel);
            }
        };
    }
}


