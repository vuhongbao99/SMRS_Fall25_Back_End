package com.example.smrsservice.dto.response;

import com.example.smrsservice.common.ApprovalFlow;
import com.example.smrsservice.common.ApprovalStatus;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TopicCreateByStudentResponse {
    private Integer topicId;
    private String topicTitle;
    private String topicDescription;
    private String studentName;
    private ApprovalFlow approvalFlow;
    private ApprovalStatus approvalStatus;
    private LocalDate createdDate;
}
