package edu.miu.sa.batchprocessor.listener;

import edu.miu.sa.batchprocessor.entity.Job;
import edu.miu.sa.batchprocessor.messaging.JobCompletionPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Slf4j
@Component
public class CustomJobListener extends JobExecutionListenerSupport {

    private final JobCompletionPublisher publisher;

    @Autowired
    public CustomJobListener(JobCompletionPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.STARTED) {
            log.info("STUDENT BATCH PROCESS STARTED...!");
        }
    }
    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("STUDENT BATCH PROCESS COMPLETED...!");

            Job completedJob = new Job(
                    jobExecution.getJobInstance().getJobName(),
                    jobExecution.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),
                    jobExecution.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
            );
            publisher.publish(completedJob);
        }
    }
}