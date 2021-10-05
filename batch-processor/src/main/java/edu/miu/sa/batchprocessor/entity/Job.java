package edu.miu.sa.batchprocessor.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Job implements Serializable {

    private String name;

    private LocalDateTime startedTime;

    private LocalDateTime finishedTime;
}
