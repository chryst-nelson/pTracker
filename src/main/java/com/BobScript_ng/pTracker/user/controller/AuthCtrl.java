package com.BobScript_ng.pTracker.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BobScript_ng.pTracker.user.dto.AuthLoginDto;
import com.BobScript_ng.pTracker.user.dto.AuthResDto;
import com.BobScript_ng.pTracker.user.dto.ReqUserDto;
import com.BobScript_ng.pTracker.user.service.AuthService;
import com.BobScript_ng.pTracker.util.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthCtrl {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthResDto>> register(
            @RequestBody @Valid ReqUserDto request) {
        return ResponseEntity.status(201)
                .body(ApiResponse.<AuthResDto>builder()
                        .status(true)
                        .data(authService.register(request))
                        .build());
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResDto>> login(
            @RequestBody @Valid AuthLoginDto request) {
        return ResponseEntity.ok(
                ApiResponse.<AuthResDto>builder()
                        .status(true)
                        .data(authService.login(request))
                        .build());
    }
}