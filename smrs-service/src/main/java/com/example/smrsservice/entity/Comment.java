package com.example.smrsservice.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @ManyToOne @JoinColumn(name = "blog_id", nullable = false)
    private Blog blog;

    // Một trong hai có thể null
    @ManyToOne @JoinColumn(name = "lecturer_id")
    private Lecturer lecturer;

    @ManyToOne @JoinColumn(name = "student_id")
    private Student student;

    @Lob
    private String content;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
