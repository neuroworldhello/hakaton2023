package com.example.backend.service.impl;

import com.example.backend.domain.Author;
import com.example.backend.domain.Category;
import com.example.backend.domain.Practice;
import com.example.backend.domain.Team;
import com.example.backend.dto.PracticeDto;
import com.example.backend.repository.AuthorRepository;
import com.example.backend.repository.CategoryRepository;
import com.example.backend.repository.PracticeRepository;
import com.example.backend.repository.TeamRepository;
import com.example.backend.service.PracticeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PracticeServiceImpl implements PracticeService {
    private final PracticeRepository practiceRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final TeamRepository teamRepository;

    public Practice savePractice(PracticeDto practiceDto) {
        Category category = categoryRepository.findByName(practiceDto.getName())
                .orElseThrow(EntityNotFoundException::new);
        Team team = teamRepository.findByName(practiceDto.getTeam())
                .orElseThrow(EntityNotFoundException::new);
        Author author = authorRepository.findByName(practiceDto.getName())
                .orElseThrow(EntityNotFoundException::new);
        Practice practice = Practice.builder()
                .name(practiceDto.getName())
                .description(practiceDto.getDescription())
                .documentLink(practiceDto.getDocumentLink())
                .rating(practiceDto.getRating())
                .author(author)
                .team(team)
                .category(category)
                .build();

        return practiceRepository.save(practice);
    }

    public List<Practice> search() {
        return practiceRepository.findAll();
    }

    public Practice getPracticeById(Long id) {
        return practiceRepository.findById(id).orElseThrow();
    }
}
