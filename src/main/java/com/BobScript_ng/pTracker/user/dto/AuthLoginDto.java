package com.BobScript_ng.pTracker.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthLoginDto {

    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
