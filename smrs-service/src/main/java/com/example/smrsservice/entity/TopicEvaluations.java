package com.example.smrsservice.entity;

import java.time.LocalDate;

public class TopicEvaluations {
    private Integer topicId;
    private Integer councilId;
    private Integer reviewerId ;
    private Double score ;
    private Enum result;
    private String comments ;
    private LocalDate evaluationDate;
}
