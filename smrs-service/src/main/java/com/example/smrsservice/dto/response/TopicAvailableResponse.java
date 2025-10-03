package com.example.smrsservice.dto.response;

import com.example.smrsservice.common.ApprovalStatus;

import java.time.LocalDate;

public class TopicAvailableResponse {
    private Integer topicId;
    private String topicTitle;
    private String topicDescription;
    private String lecturerName;
    private ApprovalStatus approvalStatus;
    private LocalDate createdDate;
}
