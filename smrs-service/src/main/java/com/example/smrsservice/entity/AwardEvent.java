package com.example.smrsservice.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "award_event")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder
public class AwardEvent {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer awardEventId;

    @Column(nullable = false)
    private String eventName;

    @Column(nullable = false)
    private Integer eventYear;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "awardEvent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AwardWinner> winners;
}
