package com.ote.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

import javax.annotation.PostConstruct;

@Service
@Slf4j
public class ConsumerService {

    @PostConstruct
    public void init() {

        log.warn("Starting ConsumerService");

        Jedis conn = new Jedis("redis", 6379);

        while (true) {
            try {
                String result = conn.rpop("correlId");
                if (result != null) {
                    log.warn(result);
                }
            } catch (JedisConnectionException e) {
                log.error("Waiting for redis", e);
            }
            sleep(1000);
        }
    }

    static void sleep(long duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
