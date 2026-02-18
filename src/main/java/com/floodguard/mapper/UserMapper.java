package com.floodguard.mapper;

import com.floodguard.dto.request.RegisterRequest;
import com.floodguard.dto.response.AuthResponse;
import com.floodguard.dto.response.UserResponse;
import com.floodguard.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", ignore = true)
    User toEntity(RegisterRequest request);

    @Mapping(target = "accessToken", ignore = true)
    @Mapping(target = "refreshToken", ignore = true)
    @Mapping(target = "role", ignore = true)
    AuthResponse toAuthResponse(User user);

    @Mapping(target = "role", expression = "java(user.getRole().name())")
    UserResponse toUserResponse(User user);
}
