package com.example.backend.repository;

import com.example.backend.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Integer> {
    Boolean existsByAuthorIdAndPracticeId(Long authorId, Long practiceId);
}
