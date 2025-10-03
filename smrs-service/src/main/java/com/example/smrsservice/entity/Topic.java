package com.example.smrsservice.entity;

import com.example.smrsservice.common.ApprovalFlow;
import com.example.smrsservice.common.ApprovalStatus;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "topic")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder
public class Topic {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer topicId;

    @Column(nullable = false)
    private String topicTitle;

    @Lob
    private String topicDescription;

    // GV hướng dẫn chính
    @ManyToOne @JoinColumn(name = "lecturer_id", nullable = false)
    private Lecturer lecturer;

    // GV hỗ trợ (nullable)
    @ManyToOne @JoinColumn(name = "mentor_id")
    private Lecturer mentor;

    // Mỗi SV chỉ có 1 topic (unique ở DB)
    @OneToOne
    @JoinColumn(name = "student_id", unique = true, nullable = false)
    private Student student;

    @Enumerated(EnumType.STRING)
    @Column(name = "approval_status", nullable = false, columnDefinition =  "varchar(20) default 'PENDING' ")
    private ApprovalStatus approvalStatus = ApprovalStatus.PENDING;

    private ApprovalFlow approvalFlow ;

    private String rejectionReason;

    @Column(name = "created_date", nullable = false)
    private java.time.LocalDate createdDate;

    // điểm (final_score là generated ở DB → read-only trong JPA)
    @Column(name = "mentor_score")  private BigDecimal mentorScore;
    @Column(name = "council_score") private BigDecimal councilScore;

    @Column(name = "final_score", insertable = false, updatable = false)
    private BigDecimal finalScore; // DB tự tính 0.4/0.6

    // nếu thầy có thêm approval_flow/approved_at ở DB mới, có thể khai báo thêm tại đây
}
