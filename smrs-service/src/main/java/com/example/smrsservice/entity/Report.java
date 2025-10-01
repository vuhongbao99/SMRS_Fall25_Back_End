package com.example.smrsservice.entity;

import com.example.smrsservice.common.ReportType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "reports")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Report {
    @Id
    private Integer reportId;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic ;

    @Enumerated(EnumType.STRING)
    private ReportType reportType;

    @Column(name = "report_file_url")
    private String reportFileUrl ;

    @Column(name = "submission_date")
    private LocalDate submissionDate;

    @Column(name = "comments")
    private String comments;
}
