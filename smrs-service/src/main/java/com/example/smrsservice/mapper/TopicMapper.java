package com.example.smrsservice.mapper;

import com.example.smrsservice.dto.request.CreateTopicByStudentRequest;
import com.example.smrsservice.dto.response.TopicCreateByStudentResponse;
import com.example.smrsservice.entity.Topic;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TopicMapper {
    Topic toTopicCreateByStudent(CreateTopicByStudentRequest request);

    TopicCreateByStudentResponse toTopicCreateByStudentResponse(Topic topic);

}
