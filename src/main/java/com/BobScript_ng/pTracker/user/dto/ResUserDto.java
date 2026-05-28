package com.BobScript_ng.pTracker.user.dto;

import java.util.ArrayList;
import java.util.List;

import com.BobScript_ng.pTracker.project.dto.ProjectResDto;
import com.BobScript_ng.pTracker.project.entity.Projects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResUserDto {

    private long id;
    private String email;

    private String fullname;

    private List<ProjectResDto> projects = new ArrayList<>();
}
