package com.example.backend.dto;

import lombok.Data;

@Data
public class PracticeDto {
    private Long id;
    private String name;
    private String description;
    private String documentLink;
    private int rating;
    private String team;
    private String category;
    private String author;
}
