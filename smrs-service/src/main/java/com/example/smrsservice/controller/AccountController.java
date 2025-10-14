package com.example.smrsservice.controller;
import com.example.smrsservice.dto.account.AccountDetailResponse;
import com.example.smrsservice.dto.account.CreateAccountDto;
import com.example.smrsservice.dto.account.PageResponse;
import com.example.smrsservice.dto.account.UpdateAccountDto;
import com.example.smrsservice.dto.auth.LoginRequest;
import com.example.smrsservice.dto.auth.LoginResponseDto;
import com.example.smrsservice.dto.common.ResponseDto;
import com.example.smrsservice.entity.Account;
import com.example.smrsservice.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

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

    @PostMapping("/create")
    void createAccount(@RequestBody CreateAccountDto request) {
        accountService.createAccount(request);
    }

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> importAccounts(
            @Parameter(description = "File Excel chứa danh sách account", required = true)
            @RequestPart("file") MultipartFile file) {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File không được để trống!");
        }

        try {
            List<Account> importedAccounts = accountService.importAccountsFromExcel(file);
            return ResponseEntity.ok(importedAccounts);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi khi import file: " + e.getMessage());
        }
    }

    @GetMapping("/list")
    PageResponse<AccountDetailResponse> getAccountDetail(@RequestParam Integer page, @RequestParam Integer size) {
        return accountService.getAccountDetail(page, size);
    }

    @DeleteMapping("/{id}")
    void deleteAccountById(@PathVariable Integer id) {
        accountService.deleteAccount(id);
    }

    @PutMapping("/update/{id}")
    AccountDetailResponse updateAccountById(@PathVariable Integer id, @RequestBody UpdateAccountDto request) {
        return accountService.updateAccount(id,request);
    }

}
