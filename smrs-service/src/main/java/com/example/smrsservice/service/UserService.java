package com.example.smrsservice.service;

import com.example.smrsservice.dto.request.UserCreationRequest;
import com.example.smrsservice.dto.response.PageResponse;
import com.example.smrsservice.dto.response.UserCreationResponse;
import com.example.smrsservice.dto.response.UserDetailResponse;
import com.example.smrsservice.dto.response.UserSearchResponse;
import com.example.smrsservice.entity.User;
import com.example.smrsservice.repository.UserRepository;
import lombok.AllArgsConstructor;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserCreationResponse createUser(UserCreationRequest request){
        if (userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already in use");
        }

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
         User user = User.builder()
                 .email(request.getEmail())
                 .passwordHash(passwordEncoder.encode(request.getPasswordHash()))
                 .build();

        userRepository.save(user);

        return UserCreationResponse.builder()
                .email(request.getEmail())
                .build();

    }

    public PageResponse<UserDetailResponse> getUsers(int page, int size){

        Pageable pageable = PageRequest.of(page - 1, size);

        Page<User> users = userRepository.findAll(pageable);

        List<User> userList = users.getContent();

        return PageResponse.<UserDetailResponse>builder()
                .currentPages(page)
                .sizes(pageable.getPageSize())
                .totalPages(users.getTotalPages())
                .totalElements(users.getTotalElements())
                .data(userList.stream().map(user -> UserDetailResponse.builder()
                        .id(user.getUserId())
                        .email(user.getEmail())
                        .build()).toList())
                .build();
    }

    public void deleteUserById(Integer id){
      User user = userRepository.findById(id)
                 .orElseThrow(() -> new RuntimeException("User not found"));

         userRepository.deleteById(user.getUserId());
    }

    public Optional<UserSearchResponse> searchUserByEmail(String email){
        return userRepository.findByEmail(email)
                .map(user -> UserSearchResponse.builder()
                        .email(user.getEmail())
                        .build()

                );


    }
}