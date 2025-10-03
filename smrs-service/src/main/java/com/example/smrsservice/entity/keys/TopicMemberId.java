package com.example.smrsservice.entity.keys;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @EqualsAndHashCode
 public class TopicMemberId implements Serializable {
    private Integer topicId;
    private Integer studentId;
}


