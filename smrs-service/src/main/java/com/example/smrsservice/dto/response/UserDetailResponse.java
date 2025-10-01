package com.example.smrsservice.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDetailResponse {
    private Integer id;
    private String email;
    
}
