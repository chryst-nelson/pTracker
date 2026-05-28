package com.BobScript_ng.pTracker.project.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BobScript_ng.pTracker.project.dto.ProjectReqDto;
import com.BobScript_ng.pTracker.project.dto.ProjectResDto;
import com.BobScript_ng.pTracker.project.service.ProjectService;
import com.BobScript_ng.pTracker.util.ApiResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class ProjectCtrl {
        private final ProjectService service;

        @PostMapping("/project/{id}")
        public ResponseEntity<ApiResponse<ProjectResDto>> createProject(@RequestBody ProjectReqDto dto,
                        @PathVariable long id) {

                ProjectResDto project = service.createProject(dto, id);
                return ResponseEntity.status(201).body(
                                ApiResponse.<ProjectResDto>builder().status(true).data(project)
                                                .build());
        }

        @GetMapping("/project")
        public ResponseEntity<ApiResponse<List<ProjectResDto>>> getProject() {

                List<ProjectResDto> project = service.getProjects();

                return ResponseEntity.status(200).body(
                                ApiResponse.<List<ProjectResDto>>builder().status(true).data(project)
                                                .build());

        }

        @GetMapping("/project/{id}")
        public ResponseEntity<ApiResponse<ProjectResDto>> getSingleProject(@PathVariable long id) {

                ProjectResDto project = service.getSingleProject(id);

                return ResponseEntity.status(200).body(
                                ApiResponse.<ProjectResDto>builder().status(true).data(project)
                                                .build());

        }

        @PutMapping("/project/{id}")
        public ResponseEntity<ApiResponse<ProjectResDto>> updateProject(@RequestBody ProjectReqDto dto,
                        @PathVariable long id) {

                ProjectResDto project = service.updateProject(dto, id);

                return ResponseEntity.status(200).body(
                                ApiResponse.<ProjectResDto>builder().status(true).data(project)
                                                .build());

        }

        @DeleteMapping("/project/{id}")
        public ResponseEntity<ApiResponse<String>> deleteProject(@PathVariable long id) {
                service.deleteProject(id);
                return ResponseEntity.status(200).body(
                                ApiResponse.<String>builder().status(true).data("Project deleted successfully")
                                                .build());
        }

}