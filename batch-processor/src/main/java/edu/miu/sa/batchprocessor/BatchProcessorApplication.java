package edu.miu.sa.batchprocessor;

import edu.miu.sa.batchprocessor.messaging.JobStartSubscriber;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class BatchProcessorApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(BatchProcessorApplication.class, args);
		JobStartSubscriber subscriber = context.getBean(JobStartSubscriber.class);
		subscriber.listen();
	}

}
