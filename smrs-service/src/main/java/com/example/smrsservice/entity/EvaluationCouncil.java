package com.example.smrsservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "evaluation_councils")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EvaluationCouncil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer councilId;

    @Column(name = "council_name")
    private String councilName;

    @Column(name = "established_date")
    private LocalDate establishedDate ;

    @OneToMany(mappedBy = "council")
    private Set<CouncilMember> members;

    @OneToMany(mappedBy = "council")
    private Set<TopicEvaluation> evaluations;
}
