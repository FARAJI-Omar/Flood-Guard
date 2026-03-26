package com.floodguard.service;

import com.floodguard.dto.request.LoginRequest;
import com.floodguard.dto.request.RefreshTokenRequest;
import com.floodguard.dto.request.RegisterRequest;
import com.floodguard.dto.response.AuthResponse;
import com.floodguard.dto.response.TokenResponse;

public interface AuthService {
    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
    
    TokenResponse refreshToken(RefreshTokenRequest request);
}
