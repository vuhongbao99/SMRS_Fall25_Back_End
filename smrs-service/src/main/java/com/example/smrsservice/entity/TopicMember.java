package com.example.smrsservice.entity;

import com.example.smrsservice.common.MemberRole;
import com.example.smrsservice.entity.keys.TopicMemberId;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "topic_member")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TopicMember {
    @EmbeddedId
    private TopicMemberId id;

    @ManyToOne
    @MapsId("topicId")
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @ManyToOne @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_role", nullable = false)
    private MemberRole memberRole;

    @Column(name = "joined_date")
    private java.time.LocalDate joinedDate;
}
