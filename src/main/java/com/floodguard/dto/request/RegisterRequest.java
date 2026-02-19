package com.floodguard.dto.request;

import com.floodguard.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank(message = "Username is required")
    private String username;
    
    @NotBlank(message = "Email is required")
    @Email(message = "This Email is not valid")
    private String email;
    
    @NotBlank(message = "Password is required")
    private String password;
    
    private Role role;
}
