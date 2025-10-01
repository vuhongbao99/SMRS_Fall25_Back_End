package com.example.smrsservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "lecturers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Lecturer {
    @Id
    private Integer lecturerId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "lecturer_id")
    private User user;

    @Column(name = "full_name")
    private String fullName ;

    @Column(name = "lecturer_code")
    private String lecturerCode ;
    private String department ;

    @Column(name = "avatar_url")
    private String avatar_url;

    private String phone;


}
