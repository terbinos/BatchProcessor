package edu.miu.sa.batchadmin.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import edu.miu.sa.batchadmin.domain.Job;
import edu.miu.sa.batchadmin.service.JobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class JobCompletionSubscriber {

    @Autowired
    JobService jobService;

    @Value("${spring.rabbitmq.job_completed}")
    private String QUEUE_JOB_COMPLETED;

    @Value("${spring.rabbitmq.host}")
    private String HOST;

    private final DeliverCallback deliverCallback = (consumerTag, delivery) -> {
        String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
        ObjectMapper objectMapper = new ObjectMapper();
        Job completedJob = objectMapper.readValue(message, Job.class);
        log.info("Saving job info");

        //todo: receive object from the queue
        jobService.save(completedJob);

    };

    public void listen() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);

        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.queueDeclare(QUEUE_JOB_COMPLETED, false, false, false, null);
            log.info("[*] Waiting for messages.");
            channel.basicConsume(QUEUE_JOB_COMPLETED, true, deliverCallback, consumerTag -> {
            });

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
