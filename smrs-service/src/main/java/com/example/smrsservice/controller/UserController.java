package com.example.smrsservice.controller;

import com.example.smrsservice.dto.request.UserCreationRequest;
import com.example.smrsservice.dto.response.PageResponse;
import com.example.smrsservice.dto.response.UserCreationResponse;
import com.example.smrsservice.dto.response.UserDetailResponse;
import com.example.smrsservice.dto.response.UserSearchResponse;
import com.example.smrsservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")

public class UserController {
    private final UserService userService;


    @PostMapping("/users")
    ResponseEntity<UserCreationResponse> createUser(@RequestBody @Valid UserCreationRequest request){
           return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(request));

    }

    @GetMapping("/users")
    PageResponse<UserDetailResponse> getUsers(@RequestParam (required = false, defaultValue = "1") int page,
                                              @RequestParam (required = false, defaultValue = "10") int size){
        return userService.getUsers(page, size);
    }

    @DeleteMapping("/users/{id}")
    void deleteUserById(@PathVariable Integer id){
        userService.deleteUserById(id);
    }

    @GetMapping("/search/{email}")
    Optional<UserSearchResponse>getUserByEmail(@PathVariable String email){
        return userService.searchUserByEmail(email);
    }



}
