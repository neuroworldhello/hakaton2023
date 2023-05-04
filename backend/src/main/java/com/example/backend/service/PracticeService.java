package com.example.backend.service;

import com.example.backend.domain.Practice;
import com.example.backend.dto.PracticeDto;
import com.example.backend.dto.PracticeSearchCriteria;

import java.util.List;

public interface PracticeService {
    PracticeDto savePractice(PracticeDto practiceDto, String author);

    List<Practice> search();

    Practice getPracticeById(Long id);

    List<Practice> searchPractices(PracticeSearchCriteria searchCriteria);

    Practice ratePractice(Long id);
}
