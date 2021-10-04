package edu.miu.sa.batchprocessor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class JobService {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;

    public void launchJob() throws Exception{
        JobParameters jobParameters = new JobParametersBuilder().addDate("date", new Date()).toJobParameters();
        jobLauncher.run(job, jobParameters);
        log.info("Job started.");
    }
}
