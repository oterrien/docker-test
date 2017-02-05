package com.ote.test;

import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Profile("rabbitmq")
@Service
@Slf4j
public class ConsumerService {

    @Value("${environment}")
    private String environment;

    @Value("${rabbitmq.queue.name}")
    private String requestQueueName;

    @Autowired
    private Channel channel;

    @PostConstruct
    public void init() throws Exception {

        log.info("####-RuleCalculatorService started");

        channel.queueDeclare(requestQueueName, true, false, false, null);
        channel.basicConsume(requestQueueName, false, createConsumer());
    }

    private Consumer createConsumer() {

        return new DefaultConsumer(channel) {

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {

                String request = new String(body, "UTF-8");
                log.info(String.format("####-'%s' received", request));
            }
        };
    }
}
