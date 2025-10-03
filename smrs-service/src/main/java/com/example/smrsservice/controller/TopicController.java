package com.example.smrsservice.controller;

import com.example.smrsservice.dto.request.CreateTopicByStudentRequest;
import com.example.smrsservice.dto.response.TopicCreateByStudentResponse;
import com.example.smrsservice.entity.Topic;
import com.example.smrsservice.service.StudentService;
import com.example.smrsservice.service.TopicService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2")
public class TopicController {
    private final TopicService topicService;
   private final StudentService studentService;

    @PostMapping("/student")
    ResponseEntity<TopicCreateByStudentResponse> createByStudent(@RequestBody @Valid CreateTopicByStudentRequest request){
        // Student currentStudent = user dang dang nhap;
        // kiem tra xem co ton tai topic nao chua hoan thanh khong
        return ResponseEntity.status(HttpStatus.CREATED).body(topicService.createByStudent(request));
    }
    @GetMapping("/student/{id}")
    ResponseEntity<?> topPicByStudent(@PathVariable Integer id){
        Topic topic = studentService.findTopicByStudentId(id);
         return new  ResponseEntity<>(topic,HttpStatus.OK);
    }

    @GetMapping("/topic")
    List<Topic> getAllTopic(){
        return topicService.findAll();
    }
}
