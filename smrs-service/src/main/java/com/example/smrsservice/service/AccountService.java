package com.example.smrsservice.service;

import com.example.smrsservice.common.AccountStatus;
import com.example.smrsservice.dto.response.AccountDetailResponse;
import com.example.smrsservice.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;

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
}
