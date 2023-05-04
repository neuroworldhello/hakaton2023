package com.example.backend.controller;

import com.example.backend.domain.Practice;
import com.example.backend.dto.PracticeDto;
import com.example.backend.dto.PracticeSearchCriteria;
import com.example.backend.service.PracticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/practices")
public class PracticeController {
    private final PracticeService practiceService;

    @PostMapping
    public PracticeDto createPractice(@RequestBody PracticeDto practiceDto) {
        return practiceService.savePractice(practiceDto);
    }

    @GetMapping("/{id}")
    public Practice getPracticeById(@PathVariable Long id) {
        return practiceService.getPracticeById(id);
    }

    @PostMapping("/search")
    public List<Practice> findPractices(@RequestBody PracticeSearchCriteria searchCriteria) {
        return practiceService.searchPractices(searchCriteria);
    }

    @PostMapping("/{id}/rate")
    public Practice ratePractice(@PathVariable("id") Long id) {
        return practiceService.ratePractice(id);
    }
}
