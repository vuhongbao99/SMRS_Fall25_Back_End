package com.example.smrsservice.entity;

import java.time.LocalDate;

public class Blogs {
    private Integer blogId ;
    private Integer topicId;
    private String blogTitle;
    private String blogContent ;
    private Enum publicationType;
    private LocalDate createdAt;
}
