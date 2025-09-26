package com.example.smrsservice.entity;

import java.time.LocalDate;

public class Reports {
    private Integer reportId;
    private Integer topicId ;
    private  Enum reportType;
    private String reportFileUrl ;
    private LocalDate submissionDate;
}
