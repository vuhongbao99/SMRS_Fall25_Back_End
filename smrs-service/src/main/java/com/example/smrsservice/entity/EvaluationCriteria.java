package com.example.smrsservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "evaluation_criteria")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder
public class EvaluationCriteria {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer criteriaId;

    @Column(nullable = false)
    private String criteriaName;

    @Lob
    private String description;

    @Column(name = "is_active")
    private Boolean isActive = true;
}
