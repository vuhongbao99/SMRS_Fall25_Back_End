package com.example.smrsservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "students")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    private Integer studentId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "student_id")
    private User user;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "student_code")
    private String studentCode;

    private String major;

    private String classes;
    private String faculty;


    @Column(name = "avatar_url")
    private String avatarUrl;

    private String phone;

    @OneToOne(mappedBy = "student")
    private Topic topic;

    @OneToMany(mappedBy = "student")
    private List<Comment>comments;

}
