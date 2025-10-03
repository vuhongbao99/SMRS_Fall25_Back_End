package com.example.smrsservice.dto.response;

import com.example.smrsservice.common.ApprovalFlow;
import com.example.smrsservice.common.ApprovalStatus;

import java.time.LocalDate;

public class TopicCreateByLecturerResponse {
    private Integer topicId;
    private String topicTitle;
    private String topicDescription;
    private String lecturerName;
    private String studentName;       // null khi mới tạo
    private ApprovalFlow approvalFlow;
    private ApprovalStatus approvalStatus;
    private LocalDate createdDate;
}
