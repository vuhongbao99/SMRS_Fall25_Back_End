package com.example.smrsservice.dto.response;

import com.example.smrsservice.entity.Role;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

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
