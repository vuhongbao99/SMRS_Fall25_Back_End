package com.example.smrsservice.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class AccountImportResponse {
    
    private int totalRecords;
    private int successfulImports;
    private int failedImports;
    private List<String> errors;
    private String message;
    
    @Getter
    @Setter
    @Builder
    public static class ImportError {
        private int rowNumber;
        private String field;
        private String error;
        private String value;
    }
}