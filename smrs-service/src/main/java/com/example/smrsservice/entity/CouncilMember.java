package com.example.smrsservice.entity;

import com.example.smrsservice.common.MemberRole;
import com.example.smrsservice.entity.keys.CouncilMemberId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "council_member")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class CouncilMember {
    @EmbeddedId
    private CouncilMemberId id;

    @ManyToOne @MapsId("councilId")
    @JoinColumn(name = "council_id")
    private EvaluationCouncil council;

    @ManyToOne @MapsId("lecturerId")
    @JoinColumn(name = "lecturer_id")
    private Lecturer lecturer;

    @Column(name = "member_role", nullable = false)
    private String memberRole; // 'Chairman','Secretary','Reviewer' (có thể chuyển sang enum nếu thầy muốn)
}
