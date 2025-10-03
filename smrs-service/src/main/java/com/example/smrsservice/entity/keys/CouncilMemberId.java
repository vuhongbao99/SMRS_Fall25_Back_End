package com.example.smrsservice.entity.keys;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class CouncilMemberId implements Serializable {
     private Integer councilId;
     private Integer lecturerId;
}
