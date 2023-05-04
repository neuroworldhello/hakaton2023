package com.example.backend.Converter;

import com.example.backend.domain.Practice;
import com.example.backend.dto.PracticeDto;

public interface PracticeConverterService {
    Practice toEntity(PracticeDto practiceDto);

    PracticeDto toDto(Practice practice);
}
