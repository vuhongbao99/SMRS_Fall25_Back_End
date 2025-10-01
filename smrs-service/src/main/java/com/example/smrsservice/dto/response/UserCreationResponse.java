package com.example.smrsservice.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserCreationResponse {
    private String email;
    private  Integer roleId;
}
