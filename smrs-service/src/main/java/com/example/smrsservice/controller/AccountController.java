//package com.example.smrsservice.controller;
//
//import com.example.smrsservice.dto.request.AccountImportRequest;
//import com.example.smrsservice.dto.response.AccountDetailResponse;
//import com.example.smrsservice.dto.response.AccountImportResponse;
//import com.example.smrsservice.exception.FileProcessingException;
//import com.example.smrsservice.exception.InvalidFileFormatException;
//import com.example.smrsservice.service.AccountService;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.security.auth.login.AccountNotFoundException;
//import java.io.IOException;
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/accounts")
//public class AccountController {
//
//  private final AccountService accountService;
//
//  @GetMapping
//  public ResponseEntity<List<AccountDetailResponse>> getAll() {
//    return ResponseEntity.ok(accountService.getAccounts());
//  }
//
//  @PatchMapping("/{id}/lock")
//  public ResponseEntity<Void> lock(@PathVariable Integer id) throws AccountNotFoundException {
//    accountService.lockAccount(id);
//    return ResponseEntity.noContent().build(); // 204
//  }
//
//  @PatchMapping("/{id}/activate")
//  public ResponseEntity<Void> activate(@PathVariable Integer id) throws AccountNotFoundException {
//    accountService.activateAccount(id);
//    return ResponseEntity.noContent().build();
//  }
//
//  @PostMapping("/import")
//  public ResponseEntity<AccountImportResponse> importAccounts(
//      @Valid @ModelAttribute AccountImportRequest request)
//      throws IOException, FileProcessingException, InvalidFileFormatException {
//    AccountImportResponse response = accountService.importAccounts(request);
//    return ResponseEntity.ok(response);
//  }
//}
