package com.example.backend.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
@Table(name = "author")
@NoArgsConstructor
public class Author {
    @Id
    @Column(name = "author_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
