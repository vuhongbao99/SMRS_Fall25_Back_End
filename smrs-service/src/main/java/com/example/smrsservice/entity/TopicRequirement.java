package com.example.smrsservice.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(
        name = "topic_requirement",
        uniqueConstraints = @UniqueConstraint(name = "uq_topic_criteria", columnNames = {"topic_id","criteria_id"})
)
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder
public class TopicRequirement {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer requirementId;

    @ManyToOne @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    @ManyToOne @JoinColumn(name = "criteria_id", nullable = false)
    private EvaluationCriteria criteria;

    @Column(precision = 4, scale = 2)
    private BigDecimal weight;            // mặc định 1.00

    @Column(name = "is_mandatory")
    private Boolean isMandatory;

    @Column(precision = 5, scale = 2, name = "min_score")
    private BigDecimal minScore;

    @Lob
    private String note;
}
