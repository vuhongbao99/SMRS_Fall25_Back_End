package com.example.smrsservice.dto.response;

import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageResponse<T>{
    private int currentPages;
    private int sizes;
    private int totalPages;
    private long totalElements;
    private List<T> data;
}
