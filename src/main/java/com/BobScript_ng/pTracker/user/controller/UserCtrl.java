package com.BobScript_ng.pTracker.user.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BobScript_ng.pTracker.user.dto.ReqUserDto;
import com.BobScript_ng.pTracker.user.dto.ResUserDto;

import com.BobScript_ng.pTracker.user.service.UserServiceImpl;
import com.BobScript_ng.pTracker.util.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserCtrl {

    private final UserServiceImpl service;

    @GetMapping("/user")
    public ResponseEntity<ApiResponse<Page<ResUserDto>>> getUsers(Pageable pageable) {
        Page<ResUserDto> user = service.getUsers(pageable);

        return ResponseEntity.status(200)
                .body(ApiResponse.<Page<ResUserDto>>builder().status(true).data(user).build()

                );
    }

    @PostMapping("/user")
    public ResponseEntity<ApiResponse<ResUserDto>> createUser(@RequestBody @Valid ReqUserDto reqUserDto) {

        ResUserDto createdUser = service.createUser(reqUserDto);

        return ResponseEntity.status(201).body(
                ApiResponse.<ResUserDto>builder().status(true).data(createdUser).build());

    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable long id) {

        service.deleteUser(id);
        return ResponseEntity.status(200).body(
                ApiResponse.<String>builder().status(true).data("User deleted successfully").build());

    }
}
