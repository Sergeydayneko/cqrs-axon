package ru.dayneko;

import lombok.Data;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
@Data
@EnableRabbit
public class AxonModule {

    @Autowired
    private  RabbitTemplate rabbitTemplate;

    public static void main(String[] args) {
        SpringApplication.run(AxonModule.class, args);
    }

    @PostConstruct
    public void sendMessage() {
        rabbitTemplate.setExchange("mock");
        rabbitTemplate.setRoutingKey("common_route");
        rabbitTemplate.convertAndSend("PEW PEW PEW ");
    }
}
