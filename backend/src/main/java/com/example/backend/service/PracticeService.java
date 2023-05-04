package com.example.backend.service;

import com.example.backend.domain.Practice;
import com.example.backend.dto.PracticeDto;

import java.util.List;

public interface PracticeService {
    PracticeDto savePractice(PracticeDto practiceDto);

    List<Practice> search();

    Practice getPracticeById(Long id);
}
