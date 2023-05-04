package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PracticeSearchCriteria {
    private String name;
    private String author;
    private String category;
    private String team;
    private Sort.Direction sortByRatingDirection = Sort.Direction.DESC;
}
