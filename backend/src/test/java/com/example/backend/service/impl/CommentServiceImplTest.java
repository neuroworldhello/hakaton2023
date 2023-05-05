package com.example.backend.service.impl;

import com.example.backend.domain.*;
import com.example.backend.repository.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@WithMockUser(username = "Test Author")
public class CommentServiceImplTest {
    @Autowired
    private CommentServiceImpl commentService;

    @Autowired
    private PracticeRepository practiceRepository;

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Test
    void getCommentsForPractice() {
        Author author = createAuthor();
        // Создаем тестовые данные
        Practice practice = createPractice();
        Comment comment1 = Comment.builder()
                .text("Test comment 1")
                .createdAt(LocalDateTime.of(2023, 5, 4, 10, 0))
                .author(author)
                .practice(practice)
                .build();
        Comment comment2 = Comment.builder()
                .text("Test comment 2")
                .createdAt(LocalDateTime.of(2023, 5, 4, 11, 0))
                .author(author)
                .practice(practice)
                .build();
        Comment comment3 = Comment.builder()
                .text("Test comment 3")
                .createdAt(LocalDateTime.of(2023, 5, 4, 12, 0))
                .author(author)
                .practice(practice)
                .build();
        commentRepository.saveAll(List.of(comment1, comment2, comment3));

        List<Comment> comments = commentService.getCommentsForPractice(practice.getId());
        assertThat(comments).hasSize(3);
        assertThat(comments.get(0).getText()).isEqualTo("Test comment 3");
        assertThat(comments.get(1).getText()).isEqualTo("Test comment 2");
        assertThat(comments.get(2).getText()).isEqualTo("Test comment 1");
    }

    @Test
    void addCommentToPractice() {
        // Создаем тестовые данные
        Practice practice = createPractice();

        String commentText = "Test comment";

        // Выполняем тестируемый метод
        Comment comment = commentService.addCommentToPractice(practice.getId(), commentText);

        // Проверяем результаты
        Assert.assertNotNull(comment);
        Assert.assertEquals(commentText, comment.getText());
        Assert.assertEquals(practice.getId(), comment.getPractice().getId());
    }

    private Practice createPractice() {
        Practice practice = Practice.builder()
                .name("Test practice")
                .description("Test description")
                .documentLink("http://test.com")
                .rating(4)
                .author(createAuthor())
                .category(createCategory())
                .team(createTeam())
                .build();
        return practiceRepository.save(practice);
    }

    private Author createAuthor() {
        Author author = Author.builder()
                .name("Test author")
                .build();
        return authorRepository.save(author);
    }

    private Category createCategory() {
        Category category = Category.builder()
                .name("Test category")
                .build();
        return categoryRepository.save(category);
    }

    private Team createTeam() {
        Team team = Team.builder()
                .name("Test team")
                .build();
        return teamRepository.save(team);
    }

    private Comment createComment(Practice practice) {
        Comment comment = Comment.builder()
                .text("Test comment")
                .author(createAuthor())
                .practice(practice)
                .createdAt(LocalDateTime.now())
                .build();
        return commentRepository.save(comment);
    }

}