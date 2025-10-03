package com.example.smrsservice.controller;

import com.example.smrsservice.dto.request.CreateTopicByStudentRequest;
import com.example.smrsservice.dto.response.TopicCreateByStudentResponse;
import com.example.smrsservice.service.TopicService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2")
public class TopicController {
    private final TopicService topicService;

    @PostMapping("/student")
    ResponseEntity<TopicCreateByStudentResponse> createByStudent(@RequestBody @Valid CreateTopicByStudentRequest request){
        // Student currentStudent = user dang dang nhap;
        // kiem tra xem co ton tai topic nao chua hoan thanh khong
        return ResponseEntity.status(HttpStatus.CREATED).body(topicService.createByStudent(request));
    }
}
