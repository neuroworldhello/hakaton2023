package com.example.backend.service.impl;

import com.example.backend.domain.Author;
import com.example.backend.domain.Practice;
import com.example.backend.repository.AuthorRepository;
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
}
