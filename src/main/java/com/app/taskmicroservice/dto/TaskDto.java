package com.app.taskmicroservice.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class TaskDto implements Serializable {

    private Long id;
    private String title;
    private String codeTask;
    private String description;
    private LocalDateTime createDate;
    private boolean active;

}
