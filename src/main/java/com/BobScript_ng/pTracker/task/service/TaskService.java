package com.BobScript_ng.pTracker.task.service;

import java.util.List;

import com.BobScript_ng.pTracker.task.dto.TaskReqDto;
import com.BobScript_ng.pTracker.task.dto.TaskResDto;

public interface TaskService {

    TaskResDto createTask(TaskReqDto taskReqDto, long projectId);

    TaskResDto getTaskById(Long id);

    TaskResDto updateTask(Long id, TaskReqDto taskReqDto);

    void deleteTask(Long id);

    List<TaskResDto> getAllTasks();

}
