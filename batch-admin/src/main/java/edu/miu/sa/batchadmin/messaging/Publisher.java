package edu.miu.sa.batchadmin.messaging;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class Publisher {

    @Value("${spring.rabbitmq.job_start}")
    private String QUEUE_JOB_START;

    @Value("${spring.rabbitmq.host}")
    private String HOST;

    public void publish(String message) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        try (Connection connection = factory.newConnection();
            Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_JOB_START, false, false, false, null);
            channel.basicPublish("", QUEUE_JOB_START, null, message.getBytes(StandardCharsets.UTF_8));
            log.info("Publisher Sent '" + message + "'");
        }
        catch(Exception e) {
            log.error(e.getMessage());
        }
    }
}
