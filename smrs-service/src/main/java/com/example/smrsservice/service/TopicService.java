package com.example.smrsservice.service;

import com.example.smrsservice.dto.request.CreateTopicByStudentRequest;
import com.example.smrsservice.dto.response.TopicCreateByStudentResponse;
import com.example.smrsservice.entity.Student;
import com.example.smrsservice.entity.Topic;
import com.example.smrsservice.mapper.TopicMapper;
import com.example.smrsservice.repository.StudentRepository;
import com.example.smrsservice.repository.TopicRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicService {
    private final TopicRepository topicRepository;
    private final StudentRepository studentRepository;
    private final TopicMapper topicMapper;


    public TopicCreateByStudentResponse createByStudent(CreateTopicByStudentRequest request){
//        Topic topic = topicMapper.toTopicCreateByStudent(request);
        Topic topic = new Topic();
        // lay student hien tai dang dang nhap;
        Student student = studentRepository.findByStudentId(4).orElse(null);

        topic.setStudent(student);
        topic.setTopicTitle(request.getTopicTitle());
        topic.setTopicDescription(request.getTopicDescription());

        Topic savedTopic = topicRepository.save(topic);

        return topicMapper.toTopicCreateByStudentResponse(savedTopic);
    }

    public Topic getTopicByStudentId(Integer studentId) {
        return topicRepository.findByStudent_StudentId(studentId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đề tài cho studentId=" + studentId));
    }

    public List<Student> getRegisteredStudents() {
        return topicRepository.findAllRegisteredStudents();
    }
    public Page<Student> getRegisteredStudents(Pageable pageable) {
        return (Page<Student>) topicRepository.findAllRegisteredStudents(pageable);
    }

    public List<Topic> findAll(){
        return topicRepository.findAll();
    }


}
