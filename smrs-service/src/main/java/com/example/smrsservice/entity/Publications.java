package com.example.smrsservice.entity;

import java.time.LocalDate;

public class Publications {
    private Integer publicationId;
    private Integer topicId ;
    private String publicationTitle ;
    private String journalName;
    private Enum publicationType;
    private LocalDate publicationDate;
}
