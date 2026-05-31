package com.BobScript_ng.pTracker.user.dto;

import java.util.ArrayList;
import java.util.List;

import com.BobScript_ng.pTracker.project.dto.ProjectResDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank
    @Size(min = 6)
    private String password;

    private List<ProjectResDto> projects = new ArrayList<>();
}