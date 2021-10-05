package edu.miu.sa.batchprocessor.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import edu.miu.sa.batchprocessor.entity.Job;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class JobCompletionPublisher {

    @Value("${spring.rabbitmq.job_completed}")
    private String QUEUE_JOB_COMPLETED;

    @Value("${spring.rabbitmq.host}")
    private String HOST;

    public void publish(Job job) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        try (Connection connection = factory.newConnection();

            Channel channel = connection.createChannel()) {
            ObjectMapper mapper = new ObjectMapper();
            String message = mapper.writeValueAsString(job);
            channel.queueDeclare(QUEUE_JOB_COMPLETED, false, false, false, null);
            channel.basicPublish("", QUEUE_JOB_COMPLETED, null, message.getBytes(StandardCharsets.UTF_8));

            log.info("Completion of job ['" + job.getName() + "'] is published.");
        }
        catch(Exception e) {
            log.error(e.getMessage());
        }
    }
}
