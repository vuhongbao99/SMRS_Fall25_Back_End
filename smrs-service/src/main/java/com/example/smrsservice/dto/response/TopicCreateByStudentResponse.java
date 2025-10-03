package com.example.smrsservice.dto.response;

import com.example.smrsservice.common.ApprovalFlow;
import com.example.smrsservice.common.ApprovalStatus;
import com.example.smrsservice.entity.Student;
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
    private Student student;
    private ApprovalFlow approvalFlow;
    private ApprovalStatus approvalStatus;
    private LocalDate createdAt;
}
