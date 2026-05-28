package com.BobScript_ng.pTracker.user.dto;

import java.util.ArrayList;
import java.util.List;

import com.BobScript_ng.pTracker.project.dto.ProjectResDto;
import com.BobScript_ng.pTracker.project.entity.Projects;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReqUserDto {

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Fullname is required")
    private String fullname;

    private List<ProjectResDto> projects = new ArrayList<>();
}