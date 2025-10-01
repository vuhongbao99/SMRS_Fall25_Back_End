package com.example.smrsservice.entity.keys;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;

import java.io.Serializable;

@Embeddable
public class CouncilMemberId implements Serializable {
     private Integer councilId;
     private Integer lecturerId;
}
