package com.BobScript_ng.pTracker.task.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.BobScript_ng.pTracker.common.exceptionHandler.ResourceNotFound;
import com.BobScript_ng.pTracker.project.entity.Projects;
import com.BobScript_ng.pTracker.project.repository.ProjectRepo;

import com.BobScript_ng.pTracker.task.dto.TaskReqDto;
import com.BobScript_ng.pTracker.task.dto.TaskResDto;
import com.BobScript_ng.pTracker.task.entity.Tasks;
import com.BobScript_ng.pTracker.task.mapper.TaskMapper;
import com.BobScript_ng.pTracker.task.repository.TaskRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepo taskRepo;
    private final TaskMapper taskMapper;
    private final ProjectRepo projectRepo;

    @Override
    public TaskResDto createTask(TaskReqDto taskReqDto, long projectId) {
        Projects project = projectRepo.findById(projectId)
                .orElseThrow(() -> new ResourceNotFound("Project not found with id " + projectId));
        Tasks taskEntity = taskMapper.toEntity(taskReqDto);
        taskEntity.setProject(project);
        taskEntity.setAssigne(project.getOwner());
        Tasks savedTask = taskRepo.save(taskEntity);
        return taskMapper.toResponseDto(savedTask);

    }

    @Override
    public TaskResDto getTaskById(Long id) {
        Tasks task = taskRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Task not found with id " + id));
        return taskMapper.toResponseDto(task);
    }

    @Override
    public TaskResDto updateTask(Long id, TaskReqDto taskReqDto) {
        Tasks task = taskRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Task not found with id " + id));
        taskMapper.updateTaskFromDto(taskReqDto, task);
        Tasks updatedTask = taskRepo.save(task);
        return taskMapper.toResponseDto(updatedTask);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Task not found with id " + id));
        taskRepo.deleteById(id);
    }

    @Override
    public List<TaskResDto> getAllTasks() {
        List<Tasks> tasks = taskRepo.findAll();
        return taskMapper.toResponseDto(tasks);
    }

}
