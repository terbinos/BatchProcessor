package edu.miu.sa.batchadmin.service;

import edu.miu.sa.batchadmin.domain.Job;
import edu.miu.sa.batchadmin.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository repository;

    @Autowired public JobServiceImpl(JobRepository repository){ this.repository = repository; }

    @Override
    public List<Job> getAll() {
        return repository.findAll();
    }

    @Override
    public void save(Job job) {
        repository.save(job);
    }


}
