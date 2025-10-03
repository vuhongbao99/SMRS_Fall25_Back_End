package com.example.smrsservice.entity.keys;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AwardWinnerId implements Serializable {
    private Integer awardEventId;
    private String awardRank; // '1','2','3'
}
