package com.example.smrsservice.repository;

import com.example.smrsservice.entity.Student;
import com.example.smrsservice.entity.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TopicRepository extends JpaRepository<Topic,Integer> {

    Optional<Topic> findByStudent_StudentId(Integer studentId);

    @Query("SELECT t.student FROM Topic t")
    List<Student> findAllRegisteredStudents();

    @Query("SELECT t.student FROM Topic t")
    Page<Student> findAllRegisteredStudents(Pageable pageable);


}
