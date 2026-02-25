package com.floodguard.service;

import com.floodguard.dto.request.LoginRequest;
import com.floodguard.dto.request.RegisterRequest;
import com.floodguard.dto.response.AuthResponse;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
