package com.example.smrsservice.dto.request;

import com.example.smrsservice.common.ApprovalStatus;

public class ApproveTopicRequest {
    private Integer topicId;
    private ApprovalStatus approvalStatus;  // APPROVED / REJECTED
    private String rejectionReason;         // nếu bị reject
}
