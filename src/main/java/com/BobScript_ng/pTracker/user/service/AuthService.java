package com.BobScript_ng.pTracker.user.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.BobScript_ng.pTracker.common.exceptionHandler.DuplicationException;
import com.BobScript_ng.pTracker.common.exceptionHandler.ResourceNotFound;
import com.BobScript_ng.pTracker.user.entity.Role;
import com.BobScript_ng.pTracker.user.entity.User;
import com.BobScript_ng.pTracker.user.dto.AuthLoginDto;
import com.BobScript_ng.pTracker.user.dto.AuthResDto;
import com.BobScript_ng.pTracker.user.dto.ReqUserDto;
import com.BobScript_ng.pTracker.user.mapper.UserMapper;
import com.BobScript_ng.pTracker.user.repository.UserRepo;
import com.BobScript_ng.pTracker.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    public AuthResDto register(ReqUserDto request) {
        if (userRepo.existsByEmail(request.getEmail())) {
            throw new DuplicationException("Email already exists");
        }
        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);

        User savedUser = userRepo.save(user);
        String token = jwtUtil.generateToken(savedUser);
        return new AuthResDto(token, savedUser.getEmail(), savedUser.getRole().name());
    }

    public AuthResDto login(AuthLoginDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()));

        User user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFound("User not found"));

        String token = jwtUtil.generateToken(user);
        return new AuthResDto(token, user.getEmail(), user.getRole().name());
    }
}
