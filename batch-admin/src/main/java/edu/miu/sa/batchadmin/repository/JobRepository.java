package edu.miu.sa.batchadmin.repository;

import edu.miu.sa.batchadmin.domain.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
}
