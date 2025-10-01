package com.example.smrsservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter

public class UserCreationRequest {
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@(fpt\\.edu\\.vn|fe\\.edu\\.vn)$",
            message = "Email must be in the format abc@fpt.edu.vn (student) or abc@fe.edu.vn (teacher)\nPassword must be at least 8 characters long and include uppercase letters, lowercase letters, numbers, and special characters"
    )
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must contain at least 8 characters, including uppercase, lowercase, digits, and special symbols\n")
    @NotBlank(message = "password cannot be blank")
    private String passwordHash;


}
