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
public class TaskReqDto {

    private String title;

    private String description;

    private TaskStatus status;

    private LocalDateTime dead_line;

    private Long project_id;

    private Long assignee_id;
}