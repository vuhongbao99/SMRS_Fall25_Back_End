package com.example.smrsservice.dto.request;

import com.example.smrsservice.common.ApprovalFlow;
import com.example.smrsservice.entity.Lecturer;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTopicByStudentRequest {
    @NotBlank(message = "Tittle is not blank")
    private String fullName;
    @NotBlank(message = "Tittle is not blank")
    private String topicTitle;

    @NotBlank(message = "Description is not blank")
    private String topicDescription;
    private ApprovalFlow approvalFlow;
}
