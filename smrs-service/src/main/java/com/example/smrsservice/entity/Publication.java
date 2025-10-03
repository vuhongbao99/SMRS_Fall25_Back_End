package com.example.smrsservice.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "publication")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder
public class Publication {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer publicationId;

    @ManyToOne @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    @Column(nullable = false)
    private String publicationTitle;

    private String journalName;

    @Column(name = "publication_type", nullable = false)
    private String publicationType; // 'Conference','Journal','Other'

    @Column(name = "publication_date", nullable = false)
    private LocalDate publicationDate;

    @Column(name = "status")
    private String status; // 'Submitted','Accepted','Rejected'
}
