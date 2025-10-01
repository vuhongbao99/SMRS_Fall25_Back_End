package com.example.smrsservice.entity;

import com.example.smrsservice.common.EvaluationResult;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "topic_evaluations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TopicEvaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer evaluationId;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @ManyToOne
    @JoinColumn(name = "council_id")
    private EvaluationCouncil council;

    @ManyToOne
    @JoinColumn(name = "reviewer_id")
    private Lecturer reviewer ;

    private Double score ;

    @Enumerated(EnumType.STRING)
    private EvaluationResult result;


    private String comments ;

    @Column(name = "evaluation_date")
    private LocalDate evaluationDate;
}
