package com.example.smrsservice.entity;

import com.example.smrsservice.entity.keys.AwardWinnerId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mapstruct.Builder;

import java.math.BigDecimal;

@Entity
@Table(name = "award_winner")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class AwardWinner {
    @EmbeddedId
    private AwardWinnerId id;

    @ManyToOne
    @MapsId("awardEventId")
    @JoinColumn(name = "award_event_id")
    private AwardEvent awardEvent;

    @ManyToOne @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    @Column(name = "final_score", nullable = false)
    private BigDecimal finalScore; // copy từ topics.final_score tại thời điểm chốt

    private String note;
}
