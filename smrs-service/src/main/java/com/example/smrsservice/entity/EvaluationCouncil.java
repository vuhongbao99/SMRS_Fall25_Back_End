package com.example.smrsservice.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "evaluation_council")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder
public class EvaluationCouncil {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer councilId;

    @Column(nullable = false)
    private String councilName;

    @Column(nullable = false)
    private LocalDate establishedDate;

    @OneToMany(mappedBy = "council", fetch = FetchType.LAZY)
    private List<CouncilMember> members;
}
