package com.example.smrsservice.dto.request;

import com.example.smrsservice.common.ApprovalStatus;

import java.time.LocalDate;

public class RegisterTopicRequest {
    private  Integer topicId;
    private String topicTitle;
    private String topicDescription;
    private String lecturerName;
    private String studentName;
    private ApprovalStatus approvalStatus;   // Pending / Approved / Rejected
    private LocalDate createdDate;

}
