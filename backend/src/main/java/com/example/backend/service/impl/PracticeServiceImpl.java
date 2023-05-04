package com.example.backend.service.impl;

import com.example.backend.Converter.PracticeConverterService;
import com.example.backend.domain.Practice;
import com.example.backend.dto.PracticeDto;
import com.example.backend.dto.PracticeSearchCriteria;
import com.example.backend.repository.PracticeRepository;
import com.example.backend.service.PracticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

import static com.example.backend.repository.criteria.PracticeSpecification.*;

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

    @Override
    public List<Practice> searchPractices(PracticeSearchCriteria searchCriteria) {
        Specification<Practice> spec = Specification.where(null);

        if (searchCriteria.getAuthor() != null) {
            spec = spec.and(byAuthor(searchCriteria.getAuthor()));
        }

        if (searchCriteria.getCategory() != null) {
            spec = spec.and(byCategory(searchCriteria.getCategory()));
        }

        if (searchCriteria.getTeam() != null) {
            spec = spec.and(byTeam(searchCriteria.getTeam()));
        }

        if (searchCriteria.getName() != null) {
            spec = spec.and(byName(searchCriteria.getName()));
        }

        Sort sort = Sort.by(searchCriteria.getSortByRatingDirection(), "rating");

        return practiceRepository.findAll(spec, sort);
    }

    @Override
    public Practice ratePractice(Long id) {
        Practice practice = practiceRepository.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException("Practice not found with id: " + id));

        practice.setRating(practice.getRating() + 1);

        return practiceRepository.save(practice);
    }
}
