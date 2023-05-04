package com.example.backend.converter.impl;

import com.example.backend.converter.PracticeConverterService;
import com.example.backend.domain.Author;
import com.example.backend.domain.Category;
import com.example.backend.domain.Practice;
import com.example.backend.domain.Team;
import com.example.backend.dto.PracticeDto;
import com.example.backend.repository.AuthorRepository;
import com.example.backend.repository.CategoryRepository;
import com.example.backend.repository.TeamRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PracticeConverterServiceImpl implements PracticeConverterService {
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final TeamRepository teamRepository;

    @Override
    public Practice toEntity(PracticeDto practiceDto) {
        Category category = categoryRepository.findByName(practiceDto.getCategory())
                .orElseThrow(EntityNotFoundException::new);
        Team team = teamRepository.findByName(practiceDto.getTeam())
                .orElseThrow(EntityNotFoundException::new);
        Author author = authorRepository.findByName(practiceDto.getAuthor())
                .orElseGet(() -> createAuthor(practiceDto.getAuthor()));
        return Practice.builder()
                .id(practiceDto.getId())
                .name(practiceDto.getName())
                .description(practiceDto.getDescription())
                .documentLink(practiceDto.getDocumentLink())
                .rating(practiceDto.getRating())
                .author(author)
                .team(team)
                .category(category)
                .build();
    }

    private Author createAuthor(String author) {
        return authorRepository.save(Author.builder().name(author).build());
    }

    @Override
    public PracticeDto toDto(Practice practice) {
        return PracticeDto.builder()
                .id(practice.getId())
                .name(practice.getName())
                .description(practice.getDescription())
                .documentLink(practice.getDocumentLink())
                .rating(practice.getRating())
                .author(practice.getAuthor().getName())
                .team(practice.getTeam().getName())
                .category(practice.getCategory().getName())
                .build();
    }
}
