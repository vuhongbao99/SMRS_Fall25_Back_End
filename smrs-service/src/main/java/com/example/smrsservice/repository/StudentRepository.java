package com.example.smrsservice.repository;

import com.example.smrsservice.entity.Student;
import com.example.smrsservice.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findByStudentId(Integer studentId);


    Topic findTopicByStudentId(Integer id);
}
