package edu.miu.sa.batchadmin.service;

import edu.miu.sa.batchadmin.domain.Job;

import java.util.List;

public interface JobService {
    List<Job> getAll();

    void save(Job job);
}
