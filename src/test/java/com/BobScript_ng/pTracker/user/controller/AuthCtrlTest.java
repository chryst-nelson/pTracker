package com.BobScript_ng.pTracker.user.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.BobScript_ng.pTracker.config.SecurityConfig;
import com.BobScript_ng.pTracker.user.dto.AuthLoginDto;
import com.BobScript_ng.pTracker.user.dto.AuthResDto;
import com.BobScript_ng.pTracker.user.dto.ReqUserDto;
import com.BobScript_ng.pTracker.user.dto.ResUserDto;
import com.BobScript_ng.pTracker.user.service.AuthService;
import com.BobScript_ng.pTracker.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(AuthCtrl.class)
@Import(SecurityConfig.class)
class AuthCtrlTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @MockitoBean
    private AuthService authService;

    @MockitoBean
    private JwtUtil jwtUtil;

    @MockitoBean
    private UserDetailsService userDetailsService;

    @Test
    void register_shouldReturn201_whenValidRequest() throws Exception {
        ReqUserDto request = new ReqUserDto("bob@test.com", "Bob", "secret123");

        ResUserDto response = new ResUserDto();
        response.setEmail("bob@test.com");
        response.setFullname("Bob");

        when(authService.register(any(ReqUserDto.class))).thenReturn(response);

        mockMvc.perform(post("/api/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.email").value("bob@test.com"));
    }

    @Test
    void register_shouldReturn400_whenEmailIsBlank() throws Exception {
        ReqUserDto request = new ReqUserDto("", "Bob", "secret123");

        mockMvc.perform(post("/api/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void login_shouldReturn200_withToken() throws Exception {
        AuthLoginDto request = new AuthLoginDto();
        request.setEmail("bob@test.com");
        request.setPassword("secret123");
        AuthResDto response = new AuthResDto("mocked.token", "bob@test.com", "USER");

        when(authService.login(any(AuthLoginDto.class))).thenReturn(response);

        mockMvc.perform(post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.token").value("mocked.token"))
                .andExpect(jsonPath("$.data.email").value("bob@test.com"));
    }
}