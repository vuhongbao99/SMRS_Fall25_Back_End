package com.example.smrsservice.response;

import com.example.smrsservice.entity.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AccountDetailResponse {
    private Integer id;
    private String email;
    private String avatar;
    private String phone;
    private String name;
    private Integer age;
    private String status = "Active";
    private Role role;
    private boolean locked;
}
