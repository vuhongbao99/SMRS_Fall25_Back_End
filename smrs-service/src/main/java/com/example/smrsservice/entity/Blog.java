package com.example.smrsservice.entity;

import com.example.smrsservice.common.Visibility;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Entity
@Table(name = "blogs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer blogId ;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @Column(name = "blog_title")
    private String blogTitle;

    @Column(name = "blog_content")
    private String blogContent ;

    @Enumerated(EnumType.STRING)
    private Visibility visibility;

    @Column(name = "created_at")
    private LocalDate createdAt;
}
