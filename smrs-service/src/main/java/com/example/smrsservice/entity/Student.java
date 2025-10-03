package com.example.smrsservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "student")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Student {
    @Id
    @Column(name = "student_id")
    private Integer studentId; // = userId

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "student_code", unique = true, nullable = false)
    private String studentCode;

    private String major;
    private String classes;
    private String faculty;
    private String avatarUrl;
    private String phone;
}
