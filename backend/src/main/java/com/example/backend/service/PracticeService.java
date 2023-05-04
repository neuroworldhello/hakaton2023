package com.example.backend.service;

import com.example.backend.domain.Practice;
import com.example.backend.dto.PracticeDto;
import com.example.backend.dto.PracticeSearchCriteriaDTO;

import java.util.List;

public interface PracticeService {
    PracticeDto savePractice(PracticeDto practiceDto);

    List<Practice> search();

    Practice getPracticeById(Long id);

    List<Practice> searchPractices(PracticeSearchCriteriaDTO searchCriteria);
}
