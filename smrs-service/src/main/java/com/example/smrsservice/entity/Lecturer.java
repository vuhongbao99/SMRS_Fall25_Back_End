package com.example.smrsservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "lecturer")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Lecturer {
    @Id
    @Column(name = "lecturer_id")
    private Integer lecturerId; // = userId

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "lecturer_code", unique = true, nullable = false)
    private String lecturerCode;

    private String department;
    private String avatarUrl;
    private String phone;
}
