package com.example.smrsservice.mapper;

import com.example.smrsservice.dto.request.UserCreationRequest;
import com.example.smrsservice.dto.response.UserCreationResponse;
import com.example.smrsservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "passwordHash",ignore = true)
    User toUser(UserCreationRequest request);

    UserCreationResponse toUserResponse (User user);
}
