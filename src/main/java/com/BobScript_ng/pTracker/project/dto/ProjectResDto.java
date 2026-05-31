package com.BobScript_ng.pTracker.project.dto;

import java.util.List;

import com.BobScript_ng.pTracker.task.dto.TaskResDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResDto {

    private Long id;
    private String name;

    private Long ownerId;
    private String OwnerName;
    private List<TaskResDto> tasks;

}
