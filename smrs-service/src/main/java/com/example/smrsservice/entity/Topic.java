package com.example.smrsservice.entity;

import com.example.smrsservice.common.ApprovalStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Entity
@Table(name = "topics")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Topic {
    @Id
    private Integer topic_id;

    @Column(name = "topic_title")
    private String topicTitle;

    @Column(name = "topic_description")
    private String topicDescription;

    @ManyToOne
    @JoinColumn(name = "lecturer_id")
    private Lecturer lecturer;

    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private Lecturer mentor;

    @OneToOne
    @JoinColumn(name = "student_id", unique = true)
    private Student student;

    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus = ApprovalStatus.PENDING;

    @Column(name = "rejection_reason")
    private String rejectionReason;

    @Column(name = "created_date")
    private LocalDate createdDate ;
}
