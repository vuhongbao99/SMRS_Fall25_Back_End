package com.example.smrsservice.service;

import com.example.smrsservice.dto.request.CreateTopicByStudentRequest;
import com.example.smrsservice.dto.response.TopicCreateByStudentResponse;
import com.example.smrsservice.entity.Topic;
import com.example.smrsservice.mapper.TopicMapper;
import com.example.smrsservice.repository.TopicRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TopicService {
    private final TopicRepository topicRepository;
    private final TopicMapper topicMapper;


    public TopicCreateByStudentResponse createByStudent(CreateTopicByStudentRequest request){
        Topic topic = topicMapper.toTopicCreateByStudent(request);

        topicRepository.save(topic);

        return topicMapper.toTopicCreateByStudentResponse(topic);
    }
}
