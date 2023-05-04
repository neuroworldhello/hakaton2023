package com.example.backend.repository;

import com.example.backend.domain.Practice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PracticeRepository extends JpaRepository<Practice, Long>,
        JpaSpecificationExecutor<Practice> {
}
