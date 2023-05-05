package com.example.backend.service;

import com.example.backend.domain.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getCommentsByPracticeId(Long practiceId);
    Comment addComment(Long practiceId, String text);
}
