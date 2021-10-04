package edu.miu.sa.batchadmin.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Entity
public class Job {
    @Id @GeneratedValue
    private Long id;

    private String name;

    private LocalDateTime startedTime;

    private LocalDateTime finishedTime;
}
