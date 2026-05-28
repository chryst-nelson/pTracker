package com.BobScript_ng.pTracker.project.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.BobScript_ng.pTracker.project.dto.ProjectReqDto;
import com.BobScript_ng.pTracker.project.dto.ProjectResDto;
import com.BobScript_ng.pTracker.project.entity.Projects;
import com.BobScript_ng.pTracker.task.mapper.TaskMapper;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = TaskMapper.class)
public interface ProjectMapper {

    @Mapping(target = "owner", ignore = true)
    Projects toEntity(ProjectReqDto dto);

    @Mapping(target = "owner", ignore = true)
    void updateProjectFromDto(ProjectReqDto dto, @MappingTarget Projects project);

    @Mapping(source = "owner.id", target = "ownerId")
    @Mapping(source = "owner.fullname", target = "ownerName")
    @Mapping(source = "tasks", target = "tasks")

    ProjectResDto toResponseDto(Projects project);

    List<ProjectResDto> toResponseDto(List<Projects> project);

}