package com.example.smrsservice.service;

import com.example.smrsservice.dto.request.LoginRequest;
import com.example.smrsservice.dto.response.LoginResponse;
import com.example.smrsservice.entity.Account;
import com.example.smrsservice.repository.AccountRepository;
import com.example.smrsservice.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final AccountRepository accountRepository;
  private final JwtUtil jwtUtil;
  private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  @Value("${jwt.expiration:86400000}") // 24 hours in milliseconds
  private Long jwtExpiration;

  public LoginResponse login(LoginRequest loginRequest) {
    // Find account by email
    Optional<Account> accountOpt = accountRepository.findByEmail(loginRequest.getEmail());

    if (accountOpt.isEmpty()) {
      throw new RuntimeException("Invalid email or password");
    }

    Account account = accountOpt.get();

    // Verify password
    if (!passwordEncoder.matches(loginRequest.getPassword(), account.getPassword())) {
      throw new RuntimeException("Invalid email or password");
    }

    // Check if account is active
    if (account.getStatus() != com.example.smrsservice.common.AccountStatus.ACTIVE) {
      throw new RuntimeException("Account is not active");
    }

    // Generate JWT token with additional claims
    Map<String, Object> claims = new HashMap<>();
    claims.put("userId", account.getId());
    claims.put("role", account.getRole() != null ? account.getRole().getRoleName() : "USER");

    String token = jwtUtil.generateToken(account.getEmail(), claims);

    // Build response
    return LoginResponse.builder()
        .token(token)
        .tokenType("Bearer")
        .expiresIn(jwtExpiration / 1000) // Convert to seconds
        .user(LoginResponse.UserInfo.builder()
            .id(account.getId())
            .email(account.getEmail())
            .name(account.getName())
            .role(account.getRole() != null ? account.getRole().getRoleName() : "USER")
            .build())
        .build();
  }
}