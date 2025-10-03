package com.example.smrsservice.entity;

import com.example.smrsservice.common.ReportType;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "report")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder
public class Report {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reportId;

    @ManyToOne @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    @Enumerated(EnumType.STRING)
    @Column(name = "report_type", nullable = false)
    private ReportType reportType;

    @Column(name = "report_file_url", nullable = false)
    private String reportFileUrl;

    @Column(name = "submission_date", nullable = false)
    private LocalDateTime submissionDate;

    private String comments;
}
