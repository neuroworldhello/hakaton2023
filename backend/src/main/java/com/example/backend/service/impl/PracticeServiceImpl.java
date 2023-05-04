package com.example.backend.service.impl;

import com.example.backend.Converter.PracticeConverterService;
import com.example.backend.domain.Practice;
import com.example.backend.dto.PracticeDto;
import com.example.backend.repository.PracticeRepository;
import com.example.backend.service.PracticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PracticeServiceImpl implements PracticeService {
    private final PracticeRepository practiceRepository;
    private final PracticeConverterService practiceConverterService;

    public PracticeDto savePractice(PracticeDto practiceDto) {

        Practice practice = practiceRepository.save(practiceConverterService.toEntity(practiceDto));
        return practiceConverterService.toDto(practice);
    }

    public List<Practice> search() {
        return practiceRepository.findAll();
    }

    public Practice getPracticeById(Long id) {
        return practiceRepository.findById(id).orElseThrow();
    }
}
