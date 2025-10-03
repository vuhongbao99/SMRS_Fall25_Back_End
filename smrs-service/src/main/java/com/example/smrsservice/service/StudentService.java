package com.example.smrsservice.service;

import com.example.smrsservice.entity.Topic;
import com.example.smrsservice.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {
     private final StudentRepository studentRepository;
    public Topic findTopicByStudentId(Integer id) {
        return studentRepository.findTopicByStudentId(id);
    }
}
