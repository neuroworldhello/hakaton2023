package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PracticeSearchCriteriaDTO {
    private String name;
    private String author;
    private String category;
    private String team;
}
