package com.BobScript_ng.pTracker.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResDto {
    private String token;
    private String email;
    private String role;
}