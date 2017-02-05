package com.ote.test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.sun.jndi.toolkit.url.Uri;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

@Profile("rabbitmq")
@Configuration
public class RabbitMQConfiguration {

    /*
    @Value("${rabbitmq.host}")
    private String host;

    @Value("${rabbitmq.port}")
    private int port;

    @Value("${rabbitmq.user}")
    private String user;

    @Value("${rabbitmq.password}")
    private String password;*/

    @Value("${rabbitmq.uri}")
    private String uri;

    @Bean
    public Connection connection() throws IOException, TimeoutException, NoSuchAlgorithmException, KeyManagementException, URISyntaxException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri(uri);
        return factory.newConnection();
    }

    @Bean
    public Channel channel(Connection connection) throws IOException {
        return connection.createChannel();
    }
}
