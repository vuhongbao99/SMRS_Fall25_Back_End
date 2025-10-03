package com.example.smrsservice.dto.response;

import com.example.smrsservice.common.ApprovalStatus;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApproveTopicResponse {
    private Integer topicId;
    private String topicTitle;
    private String studentName;
    private String lecturerName;
    private ApprovalStatus approvalStatus;
    private String rejectionReason;
    private LocalDateTime approvedAt;
}
