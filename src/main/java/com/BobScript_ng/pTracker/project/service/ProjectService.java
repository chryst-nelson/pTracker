package com.BobScript_ng.pTracker.project.service;

import java.util.List;

import com.BobScript_ng.pTracker.project.dto.ProjectReqDto;
import com.BobScript_ng.pTracker.project.dto.ProjectResDto;

public interface ProjectService {

    ProjectResDto createProject(ProjectReqDto dto, long id);

    List<ProjectResDto> getProjects();

    ProjectResDto getSingleProject(long id);

    ProjectResDto updateProject(ProjectReqDto dto, long id);

    void deleteProject(long id);

}
