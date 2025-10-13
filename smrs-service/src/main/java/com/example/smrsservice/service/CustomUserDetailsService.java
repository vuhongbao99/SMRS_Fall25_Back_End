package com.example.smrsservice.service;

import com.example.smrsservice.entity.Account;
import com.example.smrsservice.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final AccountRepository accountRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Account account = accountRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

    List<GrantedAuthority> authorities = new ArrayList<>();
    if (account.getRole() != null) {
      authorities.add(new SimpleGrantedAuthority("ROLE_" + account.getRole().getRoleName()));
    } else {
      authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
    }

    return User.builder()
        .username(account.getEmail())
        .password(account.getPassword())
        .authorities(authorities)
        .accountExpired(false)
        .accountLocked(account.getStatus() == com.example.smrsservice.common.AccountStatus.LOCKED)
        .credentialsExpired(false)
        .disabled(account.getStatus() != com.example.smrsservice.common.AccountStatus.ACTIVE)
        .build();
  }
}