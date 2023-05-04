package com.example.backend.repository;

import com.example.backend.domain.Practice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PracticeRepository extends JpaRepository<Practice, Long> {

}
