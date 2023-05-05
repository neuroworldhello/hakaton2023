package com.example.backend.service.impl;

import com.example.backend.converter.PracticeConverterService;
import com.example.backend.domain.Author;
import com.example.backend.domain.Practice;
import com.example.backend.domain.Vote;
import com.example.backend.dto.PracticeDto;
import com.example.backend.dto.PracticeSearchCriteria;
import com.example.backend.repository.AuthorRepository;
import com.example.backend.repository.PracticeRepository;
import com.example.backend.repository.VoteRepository;
import com.example.backend.service.Authorisation;
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
    private final VoteRepository voteRepository;
    private final Authorisation authorisation;
    private final AuthorRepository authorRepository;

    public PracticeDto savePractice(PracticeDto practiceDto, String author) {
        practiceDto.setAuthor(author);
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
    public List<PracticeDto> searchPractices(PracticeSearchCriteria searchCriteria) {
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

        return practiceRepository.findAll(spec, sort).stream()
                .map(practiceConverterService::toDto).toList();
    }

    @Override
    public Practice ratePractice(Long id) {
        Practice practice = practiceRepository.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException("Practice not found with id: " + id));

        practice.setRating(practice.getRating() + 1);

        Author author = authorRepository.findByName(authorisation.getUserName()).orElse(null);


        voteRepository.save(Vote.builder()
                        .practiceId(practice.getId())
                        .authorId(author.getId())
                .build());
        return practiceRepository.save(practice);
    }
}
