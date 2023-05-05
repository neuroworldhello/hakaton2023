package com.example.backend.service.impl;

import com.example.backend.domain.*;
import com.example.backend.repository.*;
import com.example.backend.service.CommentService;
import org.assertj.core.api.Assertions;
import org.junit.Assert;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class CommentServiceImplTest {

    @Autowired
    private CommentService commentService;

    @Autowired
    private PracticeRepository practiceRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Test
    @WithMockUser(username = "testUser")
    public void testGetCommentsByPracticeId() {
        Author author = authorRepository.save(Author.builder().name("Author 1").build());

        Category category = Category.builder()
                .name("Category name")
                .build();

        Team team = Team.builder()
                .name("Team name")
                .build();

        Practice practice = Practice.builder()
                .name("Practice name")
                .description("Practice description")
                .documentLink("http://example.com")
                .rating(5)
                .category(category)
                .author(author)
                .team(team)
                .build();

        Comment comment1 = Comment.builder()
                .text("Comment 1")
                .author(author)
                .practice(practice)
                .createdAt(LocalDateTime.now())
                .build();
        Comment comment2 = Comment.builder()
                .text("Comment 2")
                .author(author)
                .practice(practice)
                .createdAt(LocalDateTime.now())
                .build();

        categoryRepository.save(category);
        authorRepository.save(author);
        teamRepository.save(team);
        practiceRepository.save(practice);
        commentRepository.saveAll(List.of(comment1, comment2));

        // When
        List<Comment> comments = commentService.getCommentsByPracticeId(practice.getId());

        // Then
        Assertions.assertThat(comments)
                .extracting("text")
                .containsExactlyInAnyOrder("Comment 1", "Comment 2");
    }

}