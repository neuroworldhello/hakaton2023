package com.example.backend.service.impl;

import com.example.backend.domain.Author;
import com.example.backend.domain.Comment;
import com.example.backend.domain.Practice;
import com.example.backend.repository.CommentRepository;
import com.example.backend.repository.PracticeRepository;
import com.example.backend.service.Authorisation;
import com.example.backend.service.CommentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PracticeRepository practiceRepository;
    private final Authorisation authorisation;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository,
                              PracticeRepository practiceRepository,
                              Authorisation authorisation) {

        this.commentRepository = commentRepository;
        this.practiceRepository = practiceRepository;
        this.authorisation = authorisation;
    }

    @Override
    public List<Comment> getCommentsForPractice(Long practiceId) {
        // получаем список комментариев для практики с указанным идентификатором,
        // сортированный по дате добавления в порядке убывания
        return commentRepository.findByPracticeIdOrderByCreatedAtDesc(practiceId);
    }

    @Override
    public Comment addCommentToPractice(Long practiceId, String text) {
        // получаем текущего пользователя
        Author author = authorisation.getCurrentUser();

        // находим практику по идентификатору
        Practice practice = practiceRepository.findById(practiceId)
                .orElseThrow(() -> new EntityNotFoundException("Practice not found"));

        // создаем новый комментарий
        Comment comment = Comment.builder()
                .author(author)
                .practice(practice)
                .text(text)
                .createdAt(LocalDateTime.now())
                .build();

        // сохраняем комментарий в базе данных
        return commentRepository.save(comment);
    }
}
