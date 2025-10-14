package com.example.smrsservice.service;

import com.example.smrsservice.common.AccountStatus;
import com.example.smrsservice.dto.auth.LoginRequest;
import com.example.smrsservice.dto.auth.LoginResponseDto;
import com.example.smrsservice.dto.common.ResponseDto;
import com.example.smrsservice.repository.AccountRepository;
import com.example.smrsservice.response.AccountDetailResponse;
import com.example.smrsservice.security.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;
    private final JwtTokenUtil jwtTokenProvider;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<AccountDetailResponse> getAccounts(){
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
}
