package com.example.smrsservice.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class AccountImportRequest {
    
    @NotNull(message = "Excel file is required")
    private MultipartFile file;
    
    private Integer defaultRoleId; // Optional: default role for imported accounts
}