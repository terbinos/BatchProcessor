package edu.miu.sa.batchadmin;

import edu.miu.sa.batchadmin.messaging.JobCompletionSubscriber;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BatchAdminApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(BatchAdminApplication.class, args);

		JobCompletionSubscriber subscriber = context.getBean(JobCompletionSubscriber.class);
		subscriber.listen();
	}
}
