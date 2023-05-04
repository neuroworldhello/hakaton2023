package com.example.backend.repository.criteria;

import com.example.backend.domain.Practice;
import org.springframework.data.jpa.domain.Specification;

public class PracticeSpecification {

    public static Specification<Practice> byAuthor(String authorName) {
        return (root, query, builder) -> builder.like(root.get("author").get("name"), "%" + authorName + "%");
    }

    public static Specification<Practice> byCategory(String categoryName) {
        return (root, query, builder) -> builder.like(root.get("category").get("name"), "%" + categoryName + "%");
    }

    public static Specification<Practice> byTeam(String teamName) {
        return (root, query, builder) -> builder.like(root.get("team").get("name"), "%" + teamName + "%");
    }

    public static Specification<Practice> byName(String name) {
        return (root, query, builder) -> builder.like(root.get("name"), "%" + name + "%");
    }
}
