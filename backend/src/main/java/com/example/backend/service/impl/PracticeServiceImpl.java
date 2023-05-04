package com.example.backend.service.impl;

import com.example.backend.domain.Author;
import com.example.backend.domain.Practice;
import com.example.backend.dto.PracticeSearchCriteriaDTO;
import com.example.backend.repository.AuthorRepository;
import com.example.backend.repository.PracticeRepository;
import com.example.backend.service.PracticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.backend.repository.criteria.PracticeSpecification.*;

@Service
@Transactional
@RequiredArgsConstructor
public class PracticeServiceImpl implements PracticeService {
    private final PracticeRepository practiceRepository;
    private final AuthorRepository authorRepository;

    public Practice savePractice(Practice practice) {
        Author author = practice.getAuthor();
        if (author.getId() == null) {
            Author saved = authorRepository.save(author);
            practice.setAuthor(saved);
        }
        return practiceRepository.save(practice);
    }

    public List<Practice> search() {
        return practiceRepository.findAll();
    }

    public Practice getPracticeById(Long id) {
        return practiceRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Practice> searchPractices(PracticeSearchCriteriaDTO searchCriteria) {
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

        return practiceRepository.findAll(spec);
    }
}
