package com.example.backend.service.impl;

import com.example.backend.domain.Author;
import com.example.backend.domain.Comment;
import com.example.backend.domain.Practice;
import com.example.backend.repository.CommentRepository;
import com.example.backend.repository.PracticeRepository;
import com.example.backend.service.Authorisation;
import com.example.backend.service.CommentService;
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
    public List<Comment> getCommentsByPracticeId(Long practiceId) {
        return commentRepository.findByPracticeId(practiceId);
    }

    @Override
    public Comment addComment(Long practiceId, String text) {
        Practice practice = practiceRepository.findById(practiceId)
                .orElseThrow(() -> new RuntimeException("Practice not found"));

        Author author = authorisation.getCurrentUser();

        Comment comment = Comment.builder()
                .text(text)
                .practice(practice)
                .author(author)
                .createdAt(LocalDateTime.now())
                .build();

        return commentRepository.save(comment);
    }
}
