package com.example.backend.controller;

import com.example.backend.domain.Practice;
import com.example.backend.dto.PracticeDto;
import com.example.backend.dto.PracticeSearchCriteria;
import com.example.backend.service.PracticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/practices")
public class PracticeController {
    private final PracticeService practiceService;

    @PostMapping
    public PracticeDto createPractice(@RequestBody PracticeDto practiceDto, Authentication authentication) {
        return practiceService.savePractice(practiceDto, authentication.getName());
    }

    @GetMapping("/{id}")
    public Practice getPracticeById(@PathVariable Long id) {
        return practiceService.getPracticeById(id);
    }

    @PostMapping("/search")
    public Page<PracticeDto> searchPractices(@RequestBody PracticeSearchCriteria searchCriteria) {
        return practiceService.searchPractices(searchCriteria);
    }

    @PostMapping("/{id}/rate")
    public Practice ratePractice(@PathVariable("id") Long id) {
        return practiceService.ratePractice(id);
    }
}
