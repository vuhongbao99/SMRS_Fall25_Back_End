package com.example.smrsservice.entity;

import com.example.smrsservice.common.EvaluationResult;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "topic_evaluation")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder
public class TopicEvaluation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer evaluationId;

    @ManyToOne @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    @ManyToOne @JoinColumn(name = "council_id", nullable = false)
    private EvaluationCouncil council;

    @ManyToOne @JoinColumn(name = "reviewer_id", nullable = false)
    private Lecturer reviewer;

    @Column(nullable = false)
    private BigDecimal score;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EvaluationResult result;

    private String comments;

    @Column(name = "evaluation_date", nullable = false)
    private LocalDate evaluationDate;
}
