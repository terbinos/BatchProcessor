package edu.miu.sa.batchprocessor.messaging;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import edu.miu.sa.batchprocessor.JobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class Subscriber {

    @Autowired
    JobService jobService;

    @Value("${spring.rabbitmq.queue}")
    private String QUEUE_NAME;

    @Value("${spring.rabbitmq.host}")
    private String HOST;

    final DeliverCallback deliverCallback = (consumerTag, delivery) -> {
        String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
        log.info("Subscriber Received '" + message + "'");

        try {
            jobService.launchJob();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    };

    public void listen() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);

        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            log.info("[*] Waiting for messages.");
            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
            });

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
