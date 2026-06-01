package com.BobScript_ng.pTracker.user.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.BobScript_ng.pTracker.common.exceptionHandler.DuplicationException;
import com.BobScript_ng.pTracker.user.dto.AuthLoginDto;
import com.BobScript_ng.pTracker.user.dto.AuthResDto;
import com.BobScript_ng.pTracker.user.dto.ReqUserDto;
import com.BobScript_ng.pTracker.user.dto.ResUserDto;
import com.BobScript_ng.pTracker.user.entity.Role;
import com.BobScript_ng.pTracker.user.entity.User;
import com.BobScript_ng.pTracker.user.mapper.UserMapper;
import com.BobScript_ng.pTracker.user.repository.UserRepo;
import com.BobScript_ng.pTracker.util.JwtUtil;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepo userRepo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private AuthService authService;

    @Test
    void register_shouldSaveUserAndReturnResponse() {
        // ARRANGE - set up the scenario
        ReqUserDto request = new ReqUserDto("bob@test.com", "Bob", "secret123");

        User mockUser = new User();
        mockUser.setEmail("bob@test.com");
        mockUser.setFullname("Bob");

        ResUserDto expectedResponse = new ResUserDto();
        expectedResponse.setEmail("bob@test.com");

        when(userRepo.existsByEmail("bob@test.com")).thenReturn(false);
        when(userMapper.toEntity(request)).thenReturn(mockUser);
        when(passwordEncoder.encode("secret123")).thenReturn("hashedPassword");
        when(userRepo.save(any(User.class))).thenReturn(mockUser);
        when(userMapper.toResponse(mockUser)).thenReturn(expectedResponse);

        // ACT - call the method
        ResUserDto result = authService.register(request);

        // ASSERT - verify the result
        assertThat(result.getEmail()).isEqualTo("bob@test.com");
        verify(userRepo).save(any(User.class));
        verify(passwordEncoder).encode("secret123");
    }

    @Test
    void register_shouldThrowException_whenEmailAlreadyExists() {
        ReqUserDto request = new ReqUserDto("bob@test.com", "Bob", "secret123");

        when(userRepo.existsByEmail("bob@test.com")).thenReturn(true);

        assertThatThrownBy(() -> authService.register(request))
                .isInstanceOf(DuplicationException.class)
                .hasMessageContaining("Email already exists");

        verify(userRepo, never()).save(any());
    }

    @Test
    void login_shouldReturnToken_whenCredentialsAreValid() {
        AuthLoginDto request = new AuthLoginDto();
        request.setEmail("bob@test.com");
        request.setPassword("secret123");

        User mockUser = new User();
        mockUser.setEmail("bob@test.com");
        mockUser.setRole(Role.USER);

        when(userRepo.findByEmail("bob@test.com")).thenReturn(Optional.of(mockUser));
        when(jwtUtil.generateToken(mockUser)).thenReturn("mocked.jwt.token");

        AuthResDto result = authService.login(request);

        assertThat(result.getToken()).isEqualTo("mocked.jwt.token");
        assertThat(result.getEmail()).isEqualTo("bob@test.com");
        assertThat(result.getRole()).isEqualTo("USER");
    }
}