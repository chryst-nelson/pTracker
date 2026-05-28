package com.BobScript_ng.pTracker.task.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.BobScript_ng.pTracker.task.dto.TaskReqDto;
import com.BobScript_ng.pTracker.task.dto.TaskResDto;
import com.BobScript_ng.pTracker.task.entity.Tasks;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "project", ignore = true)
    @Mapping(target = "assigne", ignore = true)
    @Mapping(target = "created_at", ignore = true)
    Tasks toEntity(TaskReqDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "project", ignore = true)
    @Mapping(target = "assigne", ignore = true)
    @Mapping(target = "created_at", ignore = true)
    void updateTaskFromDto(TaskReqDto dto, @MappingTarget Tasks task);

    @Mapping(source = "project.id", target = "projectId")
    @Mapping(source = "project.name", target = "projectName")
    @Mapping(source = "assigne.id", target = "assigneId")
    @Mapping(source = "assigne.fullname", target = "assigneName")
    TaskResDto toResponseDto(Tasks task);

    List<TaskResDto> toResponseDto(List<Tasks> tasks);
}