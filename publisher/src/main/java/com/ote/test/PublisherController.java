package com.ote.test;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.UUID;

@Profile("rabbitmq")
@RestController
@Slf4j
public class PublisherController {

    @Value("${environment}")
    private String environment;

    @Value("${rabbitmq.queue.name}")
    private String requestQueueName;

    @Autowired
    private Channel channel;

    @RequestMapping(method = RequestMethod.POST, value = "/push/test")
    public ResponseEntity push(@RequestBody String body) {

        final String correlationId = UUID.randomUUID().toString();

        log.info(String.format("Sending '%S' with correlId [%s]", body, correlationId));

        sendRequest(correlationId, body);

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    private void sendRequest(String correlationId, String message) {

        try {
            AMQP.BasicProperties props = new AMQP.BasicProperties.
                    Builder().
                    correlationId(correlationId).
                    build();

            channel.queueDeclare(requestQueueName, true, false, false, null);
            channel.basicPublish(StringUtils.EMPTY, requestQueueName, props, message.getBytes("UTF-8"));

            log.debug(String.format("####-'%s' sent with correlationId [%s]", message, correlationId));

        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
