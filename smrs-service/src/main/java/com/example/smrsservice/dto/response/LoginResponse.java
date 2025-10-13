package com.example.smrsservice.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginResponse {

  private String token;
  private String tokenType;
  private Long expiresIn;
  private UserInfo user;

  @Getter
  @Setter
  @Builder
  public static class UserInfo {
    private Integer id;
    private String email;
    private String name;
    private String role;
  }
}