package com.example.smrsservice.entity;

import com.example.smrsservice.common.PublicationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Entity
@Table(name = "publications")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer publicationId;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic ;

    @Column(name = "publication_title")
    private String publicationTitle ;

    @Column(name = "journal_name")
    private String journalName;

    @Enumerated(EnumType.STRING)
    private PublicationType publicationType;

    @Column(name = "publication_date")
    private LocalDate publicationDate;
}
