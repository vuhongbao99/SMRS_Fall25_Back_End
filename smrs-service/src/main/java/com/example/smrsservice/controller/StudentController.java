package com.example.smrsservice.controller;

import com.example.smrsservice.entity.Student;
import com.example.smrsservice.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@RequestMapping("/api/topics")
@RequiredArgsConstructor
public class StudentController {
    private final TopicService topicService;
    // Trả về toàn bộ sinh viên đã có đề tài
    @GetMapping("/students")
    public List<Student> getRegisteredStudents() {
        return topicService.getRegisteredStudents();
    }
    // Có phân trang
    @GetMapping("/students/page")
    public Page<Student> getRegisteredStudentsPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return topicService.getRegisteredStudents(PageRequest.of(page, size));
    }
}
