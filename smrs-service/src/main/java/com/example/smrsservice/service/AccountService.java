package com.example.smrsservice.service;

import com.example.smrsservice.common.AccountStatus;
import com.example.smrsservice.config.PasswordEncoderConfig;
import com.example.smrsservice.dto.account.AccountDetailResponse;
import com.example.smrsservice.dto.account.CreateAccountDto;
import com.example.smrsservice.dto.account.CreateResponseDto;
import com.example.smrsservice.dto.account.UpdateAccountDto;
import com.example.smrsservice.dto.auth.LoginRequest;
import com.example.smrsservice.dto.auth.LoginResponseDto;
import com.example.smrsservice.dto.common.ResponseDto;
import com.example.smrsservice.entity.Account;
import com.example.smrsservice.entity.Role;
import com.example.smrsservice.repository.AccountRepository;
import com.example.smrsservice.repository.RoleRepository;
import com.example.smrsservice.security.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.security.auth.login.AccountNotFoundException;
import java.io.InputStream;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final JwtTokenUtil jwtTokenProvider;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final PasswordEncoderConfig passwordEncoderConfig;

    public ResponseDto<LoginResponseDto> login(LoginRequest request) {
        var acc = accountRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Email not found"));

        if (!passwordEncoder.matches(request.getPassword(), acc.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtTokenProvider.generateToken(acc.getEmail(), acc.getRole().getRoleName());

        LoginResponseDto response = new LoginResponseDto();
        response.setToken(token);
        response.setEmail(acc.getEmail());
        response.setRole(acc.getRole().getRoleName());

        return ResponseDto.success(response, "Đăng nhập thành công");
    }

    public void lockAccount(Integer id) throws AccountNotFoundException {
        var account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account không tồn tại"));
        if (account.getStatus() == AccountStatus.LOCKED) {
            throw new AccountNotFoundException("Tài Khoản Đã Bị Khóa");
        }
        account.setStatus(AccountStatus.LOCKED);
        accountRepository.save(account);

    }

    public void activateAccount(Integer id) throws AccountNotFoundException {
        var account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account không tồn tại"));
        account.setStatus(AccountStatus.ACTIVE);
        accountRepository.save(account);
    }

    public CreateResponseDto createAccount(CreateAccountDto request) {
        if (accountRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email đã tồn tại");

        }
        Account account = Account.builder()
                .email(request.getEmail())
                .name(request.getName())
                .age(request.getAge())
                .phone(request.getPhone())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRoleId())
                .status(AccountStatus.ACTIVE)
                .build();

        accountRepository.save(account);

        return CreateResponseDto.builder()
                .accountId(account.getId())
                .email(account.getEmail())
                .name(account.getName())
                .role(account.getRole())
                .status(account.getStatus())
                .build();

    }

    public List<Account> importAccountsFromExcel(MultipartFile file) {
        List<Account> accounts = new ArrayList<>();

        try (InputStream is = file.getInputStream(); Workbook workbook = new XSSFWorkbook(is)) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row row = rows.next();

                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                String email = getCellValue(row.getCell(0));
                if (email == null || email.isBlank()) continue;

                Account acc = accountRepository.findByEmail(email).orElse(new Account());
                boolean isNew = (acc.getId() == 0);

                acc.setEmail(email);
                acc.setPassword(passwordEncoder.encode(getCellValue(row.getCell(1))));
                acc.setAvatar(getCellValue(row.getCell(2)));
                acc.setPhone(getCellValue(row.getCell(3)));
                acc.setName(getCellValue(row.getCell(4)));

                String ageStr = getCellValue(row.getCell(5));
                if (!ageStr.isEmpty()) {
                    acc.setAge(Integer.parseInt(ageStr));
                }

                if (isNew) {
                    acc.setCreateDate(new Date());
                }

                String statusValue = getCellValue(row.getCell(6)).toUpperCase();
                acc.setStatus(statusValue.equals("LOCKED") ? AccountStatus.LOCKED : AccountStatus.ACTIVE);

                String roleName = getCellValue(row.getCell(7));
                Role role = roleRepository.findByRoleName(roleName).get();
                acc.setRole(role);

                accounts.add(acc);
            }

            accountRepository.saveAll(accounts);

        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi đọc file Excel: " + e.getMessage(), e);
        }

        return accounts;
    }

    private String getCellValue(Cell cell) {
        if (cell == null) return "";
        if (cell.getCellType() == CellType.STRING) return cell.getStringCellValue().trim();
        if (cell.getCellType() == CellType.NUMERIC)
            return String.valueOf((int) cell.getNumericCellValue());
        return "";
    }

    public List<AccountDetailResponse> getAccountDetail() {
        return accountRepository.findAll()
                .stream()
                .map(account -> AccountDetailResponse.builder()
                        .id(account.getId())
                        .email(account.getEmail())
                        .name(account.getName())
                        .build())
                .toList();

    }
    public void deleteAccount(Integer id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Khoong tìm thấy tài khoản"));

        accountRepository.deleteById(account.getId());
    }

    public AccountDetailResponse updateAccount(Integer id,UpdateAccountDto request) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Khoong tìm thấy tài khoản"));

        if (request.getName() != null && request.getPhone() != null) {
            account.setName(request.getName());
            account.setPhone(request.getPhone());
            accountRepository.save(account);
        }
        return AccountDetailResponse.builder()
                .id(id)
                .email(account.getEmail())
                .name(account.getName())
                .build();

    }


}