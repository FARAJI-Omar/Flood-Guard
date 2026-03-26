package com.floodguard.controller;

import com.floodguard.dto.request.UpdateProfileRequest;
import com.floodguard.dto.response.UserResponse;
import com.floodguard.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/userexists")
    public ResponseEntity<Boolean> userExists(@RequestParam String email) {
        return ResponseEntity.ok(userService.userExists(email));
    }

    @GetMapping("/me")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<UserResponse> getCurrentUser() {
        return ResponseEntity.ok(userService.getCurrentUser());
    }

    @PutMapping("/me")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<UserResponse> updateProfile(@RequestBody @Valid UpdateProfileRequest request) {
        return ResponseEntity.ok(userService.updateProfile(request));
    }
}
