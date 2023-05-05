package com.example.backend.repository;

import com.example.backend.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT c FROM Comment c WHERE c.practice.id = :practiceId")
    List<Comment> findByPracticeId(@Param("practiceId") Long practiceId);
}
