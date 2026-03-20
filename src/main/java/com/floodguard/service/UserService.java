package com.floodguard.service;

import com.floodguard.dto.request.UpdateProfileRequest;
import com.floodguard.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getAllUsers();

    boolean userExists(String email);

    UserResponse getCurrentUser();

    UserResponse updateProfile(UpdateProfileRequest request);
}
