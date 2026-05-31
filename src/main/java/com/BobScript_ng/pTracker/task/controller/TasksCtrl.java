package com.BobScript_ng.pTracker.task.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BobScript_ng.pTracker.task.service.TaskService;
import com.BobScript_ng.pTracker.util.ApiResponse;
import com.BobScript_ng.pTracker.task.dto.TaskReqDto;
import com.BobScript_ng.pTracker.task.dto.TaskResDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TasksCtrl {

    private final TaskService taskService;

    @GetMapping()
    public ResponseEntity<ApiResponse<List<TaskResDto>>> getTasks() {
        List<TaskResDto> tasks = taskService.getAllTasks();
        return ResponseEntity.status(200).body(
                ApiResponse.<List<TaskResDto>>builder().status(true).data(tasks).build());
    }

    @PostMapping("/{projectId}")
    public ResponseEntity<ApiResponse<TaskResDto>> createTask(@RequestBody TaskReqDto taskReqDto,
            @PathVariable long projectId) {
        TaskResDto createdTask = taskService.createTask(taskReqDto, projectId);
        return ResponseEntity.status(201).body(
                ApiResponse.<TaskResDto>builder().status(true).data(createdTask).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskResDto>> getTaskById(@PathVariable Long id) {
        TaskResDto task = taskService.getTaskById(id);
        return ResponseEntity.ok(
                ApiResponse.<TaskResDto>builder().status(true).data(task).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskResDto>> updateTask(@PathVariable Long id,
            @RequestBody TaskReqDto taskReqDto) {
        TaskResDto updatedTask = taskService.updateTask(id, taskReqDto);
        return ResponseEntity.ok(
                ApiResponse.<TaskResDto>builder().status(true).data(updatedTask).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok(
                ApiResponse.<String>builder().status(true).data("Task deleted successfully").build());
    }

}
