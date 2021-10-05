package edu.miu.sa.batchadmin.controller;

import edu.miu.sa.batchadmin.constants.RestEndpoints;
import edu.miu.sa.batchadmin.domain.Job;
import edu.miu.sa.batchadmin.messaging.Publisher;
import edu.miu.sa.batchadmin.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(RestEndpoints.JOB_PREFIX)
public class JobController {

    private final JobService service;
    private final Publisher publisher;


    @Autowired
    public JobController(JobService service, Publisher publisher) {
        this.service = service;
        this.publisher = publisher;
    }

    @GetMapping()
    public List<Job> getAll(){
        return service.getAll();
    }

    @GetMapping(RestEndpoints.LAUNCH_POSTFIX)
    public String launchJob() {
        publisher.publish("START");
        return "Batch job started!";
    }
}
