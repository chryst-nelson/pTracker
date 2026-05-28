package com.BobScript_ng.pTracker.task.dto;

import java.time.LocalDateTime;

import com.BobScript_ng.pTracker.task.entity.TaskStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskResDto {

    private Long id;

    private String title;

    private String description;

    private TaskStatus status;

    private LocalDateTime deadLine;

    private LocalDateTime createdAt;

    private Long projectId;

    private String projectName;

    private Long assigneId;

    private String assigneName;
}