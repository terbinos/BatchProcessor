package edu.miu.sa.batchprocessor.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Job {

    private String name;

    private LocalDateTime startedTime;

    private LocalDateTime finishedTime;
}
