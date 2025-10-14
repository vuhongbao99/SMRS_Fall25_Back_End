package com.example.smrsservice.controller;
import com.example.smrsservice.dto.auth.LoginRequest;
import com.example.smrsservice.dto.auth.LoginResponseDto;
import com.example.smrsservice.dto.common.ResponseDto;
import com.example.smrsservice.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/login")
    @PermitAll
    @Operation(summary = "Login and get JWT token")
    public ResponseDto<LoginResponseDto> login(@RequestBody LoginRequest request) {
        return accountService.login(request);
    }

    @PatchMapping("/{id}/lock")
    public ResponseEntity<Void> lock(@PathVariable Integer id) throws AccountNotFoundException {
        accountService.lockAccount(id);
        return ResponseEntity.noContent().build(); // 204
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<Void> activate(@PathVariable Integer id) throws AccountNotFoundException {
        accountService.activateAccount(id);
        return ResponseEntity.noContent().build();
    }

}
