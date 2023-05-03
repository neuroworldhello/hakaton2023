package com.example.backend.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
@Table(name = "practice")
@NoArgsConstructor
public class Practice {
    @Id
    @Column(name = "practice_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String documentLink;

    private int rating;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Practice practice = (Practice) o;
        return id != null && Objects.equals(id, practice.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
