package com.BobScript_ng.pTracker.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BobScript_ng.pTracker.common.exceptionHandler.ResourceNotFound;
import com.BobScript_ng.pTracker.project.dto.ProjectReqDto;
import com.BobScript_ng.pTracker.project.dto.ProjectResDto;
import com.BobScript_ng.pTracker.project.entity.Projects;
import com.BobScript_ng.pTracker.project.mapper.ProjectMapper;
import com.BobScript_ng.pTracker.project.repository.ProjectRepo;
import com.BobScript_ng.pTracker.user.entity.User;
import com.BobScript_ng.pTracker.user.repository.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final UserRepo userRepo;
    private final ProjectMapper mapper;
    private final ProjectRepo projectRepo;

    @Override
    public ProjectResDto createProject(ProjectReqDto dto, long userId) {
        User owner = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFound("User not found with id " + userId));
        Projects project = mapper.toEntity(dto);
        project.setOwner(owner);
        Projects savedProject = projectRepo.save(project);
        return mapper.toResponseDto(savedProject);

    }

    @Override
    public List<ProjectResDto> getProjects() {
        List<Projects> foundProjects = projectRepo.findAll();
        return mapper.toResponseDto(foundProjects);
    }

    @Override
    public ProjectResDto getSingleProject(long id) {

        Projects projectExist = projectRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFound(
                        String.format("Project not found with id: %d", id)));

        return mapper.toResponseDto(projectExist);
    }

    @Override
    public ProjectResDto updateProject(ProjectReqDto dto, long id) {
        Projects existProject = projectRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFound(
                        String.format("Project not found with id: %d", id)));
        mapper.updateProjectFromDto(dto, existProject);
        Projects updatedProject = projectRepo.save(existProject);

        return mapper.toResponseDto(updatedProject);

    }

    @Override
    public void deleteProject(long id) {
        Projects projectExist = projectRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFound(
                        String.format("Project not found with id: %d", id)));
        projectRepo.deleteById(projectExist.getId());

    }

}
