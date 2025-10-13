package com.example.smrsservice.controller;

import com.example.smrsservice.dto.request.LoginRequest;
//import com.example.smrsservice.dto.response.LoginResponse;
//import com.example.smrsservice.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

//  private final AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
    try {
//      LoginResponse response = authService.login(loginRequest);
      return ResponseEntity.ok("ok");
    } catch (RuntimeException e) {
      // Return error details for debugging
      Map<String, Object> errorResponse = new HashMap<>();
      errorResponse.put("error", "Authentication failed");
      errorResponse.put("message", e.getMessage());
      errorResponse.put("status", HttpStatus.BAD_REQUEST.value());

      return ResponseEntity.badRequest().body(errorResponse);
    }
  }

}