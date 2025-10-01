package com.example.smrsservice.exception;

import lombok.*;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {
    private Date timestamp;
    private int status;
    private String message;
    private String path;
}
