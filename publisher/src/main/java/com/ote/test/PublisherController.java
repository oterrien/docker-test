package com.ote.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.annotation.PostConstruct;
import java.util.UUID;

@RestController
@Slf4j
public class PublisherController {

    @Value("${environment}")
    private String environment;

    @PostConstruct
    private void init(){

        log.warn("#### Environment : " + environment);
        log.warn("#### Starting PublisherController");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/push/test")
    public ResponseEntity push() {

        Jedis conn = new Jedis("redis");

        final String correlationId = UUID.randomUUID().toString();

        log.info("Push correlId: " + correlationId);
        conn.rpush("correlId", correlationId);

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
