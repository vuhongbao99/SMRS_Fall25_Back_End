package com.example.smrsservice.service;

import com.example.smrsservice.common.AccountStatus;
import com.example.smrsservice.config.SecurityConfig;
import com.example.smrsservice.dto.request.AccountImportRequest;
import com.example.smrsservice.dto.response.AccountDetailResponse;
import com.example.smrsservice.dto.response.AccountImportResponse;
import com.example.smrsservice.entity.Account;
import com.example.smrsservice.entity.Role;
import com.example.smrsservice.exception.FileProcessingException;
import com.example.smrsservice.exception.InvalidFileFormatException;
import com.example.smrsservice.repository.AccountRepository;
import com.example.smrsservice.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.security.auth.login.AccountNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountService {

  private final AccountRepository accountRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder = new SecurityConfig().passwordEncoder();

  public List<AccountDetailResponse> getAccounts() {
    return accountRepository.findAll()
        .stream()
        .map(account -> AccountDetailResponse.builder()
            .id(account.getId())
            .email(account.getEmail())
            .avatar(account.getAvatar())
            .phone(account.getPhone())
            .name(account.getName())
            .age(account.getAge())
            .build())
        .toList();

  }

  public void lockAccount(Integer id) throws AccountNotFoundException {
    var account = accountRepository.findById(id)
        .orElseThrow(() -> new AccountNotFoundException("Account không tồn tại"));
    if (account.getStatus() == AccountStatus.LOCKED) {
      throw new AccountNotFoundException("Tài Khoản Đã Bị Khóa");
    }
    account.setStatus(AccountStatus.LOCKED);

  }

  public void activateAccount(Integer id) throws AccountNotFoundException {
    var account = accountRepository.findById(id)
        .orElseThrow(() -> new AccountNotFoundException("Account không tồn tại"));
    account.setStatus(AccountStatus.ACTIVE);
  }

  public AccountImportResponse importAccounts(AccountImportRequest request)
      throws FileProcessingException, InvalidFileFormatException {
    MultipartFile file = request.getFile();
    Integer defaultRoleId = request.getDefaultRoleId();

    List<String> errors = new ArrayList<>();
    int totalRecords = 0;
    int successfulImports = 0;
    int failedImports = 0;

    // Validate file type
    if (!isExcelFile(file)) {
      throw new InvalidFileFormatException("File must be an Excel file (.xlsx or .xls)");
    }

    // Validate file size (optional - can be configured in application.properties)
    if (file.isEmpty()) {
      throw new FileProcessingException("File is empty");
    }

    // Get default role if provided
    Role defaultRole = null;
    if (defaultRoleId != null) {
      defaultRole = roleRepository.findById(defaultRoleId).orElse(null);
      if (defaultRole == null) {
        throw new FileProcessingException("Default role with ID " + defaultRoleId + " not found");
      }
    }

    try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
      Sheet sheet = workbook.getSheetAt(0);

      // Validate sheet has data
      if (sheet.getLastRowNum() < 1) {
        throw new FileProcessingException("Excel file must contain at least one data row (excluding header)");
      }

      // Skip header row (assuming first row is header)
      for (int i = 1; i <= sheet.getLastRowNum(); i++) {
        Row row = sheet.getRow(i);
        if (row == null)
          continue;

        totalRecords++;

        try {
          Account account = processRow(row, defaultRole, i + 1);
          if (account != null) {
            accountRepository.save(account);
            successfulImports++;
          } else {
            failedImports++;
            errors.add("Row " + (i + 1) + ": Failed to process account data");
          }
        } catch (Exception e) {
          failedImports++;
          errors.add("Row " + (i + 1) + ": " + e.getMessage());
        }
      }
    } catch (IOException e) {
      throw new FileProcessingException("Error reading Excel file: " + e.getMessage(), e);
    }

    String message = String.format("Import completed. %d successful, %d failed out of %d total records.",
        successfulImports, failedImports, totalRecords);

    return AccountImportResponse.builder()
        .totalRecords(totalRecords)
        .successfulImports(successfulImports)
        .failedImports(failedImports)
        .errors(errors)
        .message(message)
        .build();
  }

  private boolean isExcelFile(MultipartFile file) {
    String contentType = file.getContentType();
    return contentType != null
        && (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet") ||
            contentType.equals("application/vnd.ms-excel"));
  }

  private Account processRow(Row row, Role defaultRole, int rowNumber) throws Exception {
    // Expected columns: Email, Name, Phone, Age, Password, Role (optional)
    String email = getCellValueAsString(row.getCell(0));
    String name = getCellValueAsString(row.getCell(1));
    String phone = getCellValueAsString(row.getCell(2));
    String ageStr = getCellValueAsString(row.getCell(3));
    String password = getCellValueAsString(row.getCell(4));
    String roleName = getCellValueAsString(row.getCell(5)); // Optional

    // Validate required fields
    if (email == null || email.trim().isEmpty()) {
      throw new Exception("Email is required");
    }
    if (name == null || name.trim().isEmpty()) {
      throw new Exception("Name is required");
    }
    if (password == null || password.trim().isEmpty()) {
      throw new Exception("Password is required");
    }

    // Check if email already exists
    if (accountRepository.existsByEmail(email)) {
      throw new Exception("Email already exists: " + email);
    }

    // Parse age
    Integer age = null;
    if (ageStr != null && !ageStr.trim().isEmpty()) {
      try {
        age = Integer.parseInt(ageStr.trim());
      } catch (NumberFormatException e) {
        throw new Exception("Invalid age format: " + ageStr);
      }
    }

    // Determine role
    Role role = defaultRole;
    if (roleName != null && !roleName.trim().isEmpty()) {
      role = roleRepository.findByRoleName(roleName.trim()).orElse(defaultRole);
    }

    // Create account
    Account account = new Account();
    account.setEmail(email.trim());
    account.setName(name.trim());
    account.setPhone(phone != null ? phone.trim() : null);
    account.setAge(age);
    account.setPassword(passwordEncoder.encode(password));
    account.setRole(role);
    account.setStatus(AccountStatus.ACTIVE);
    account.setCreateDate(new Date());

    return account;
  }

  private String getCellValueAsString(Cell cell) {
    if (cell == null)
      return null;

    switch (cell.getCellType()) {
      case STRING:
        return cell.getStringCellValue();
      case NUMERIC:
        if (DateUtil.isCellDateFormatted(cell)) {
          return cell.getDateCellValue().toString();
        } else {
          return String.valueOf((long) cell.getNumericCellValue());
        }
      case BOOLEAN:
        return String.valueOf(cell.getBooleanCellValue());
      case FORMULA:
        return cell.getCellFormula();
      default:
        return null;
    }
  }
}
