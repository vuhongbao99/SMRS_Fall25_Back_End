package com.example.smrsservice.entity;

import java.time.LocalDate;

public class Topics {
    private Integer topic_id;
    private String topicTitle;
    private String topicDescription;
    private Integer lecturerId;
    private Integer studentId;
    private Enum approvalStatus;
    private String rejectionReason;
    private LocalDate createdDate ;
}
