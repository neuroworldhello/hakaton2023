package com.example.backend.controller;

import com.example.backend.domain.Practice;
import com.example.backend.service.PracticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/practices")
public class PracticeController {
    private final PracticeService practiceService;

    @PostMapping
    public Practice createPractice(@RequestBody Practice practice) {
        return practiceService.savePractice(practice);
    }

    @GetMapping("/{id}")
    public Practice getPracticeById(@PathVariable Long id) {
        return practiceService.getPracticeById(id);
    }

    @PostMapping("/search")
    public List<Practice> findPractices() {
        return practiceService.search();
    }
}
