package com.example.backend.service;

import com.example.backend.domain.Practice;

import java.util.List;

public interface PracticeService {
    Practice savePractice(Practice practice);

    List<Practice> search();

    Practice getPracticeById(Long id);
}
