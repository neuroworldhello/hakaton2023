package com.example.backend.controller;

import com.example.backend.domain.Comment;
import com.example.backend.domain.Practice;
import com.example.backend.dto.PracticeDto;
import com.example.backend.dto.PracticeSearchCriteria;
import com.example.backend.service.CommentService;
import com.example.backend.service.PracticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/practices")
public class PracticeController {
    private final PracticeService practiceService;
    private final CommentService commentService;

    @PostMapping
    public PracticeDto createPractice(@RequestBody PracticeDto practiceDto, Authentication authentication) {
        return practiceService.savePractice(practiceDto, authentication.getName());
    }

    @GetMapping("/{id}")
    public Practice getPracticeById(@PathVariable Long id) {
        return practiceService.getPracticeById(id);
    }

    @PostMapping("/search")
    public List<PracticeDto> searchPractices(@RequestBody PracticeSearchCriteria searchCriteria) {
        return practiceService.searchPractices(searchCriteria);
    }

    @PostMapping("/{id}/rate")
    public Practice ratePractice(@PathVariable("id") Long id) {
        return practiceService.ratePractice(id);
    }

    @GetMapping("/{id}/comments")
    public List<Comment> getCommentsByPracticeId(@PathVariable Long id) {
        return commentService.getCommentsByPracticeId(id);
    }

    @PostMapping("/{id}/comments")
    public Comment addComment(@PathVariable Long id, @RequestParam String text) {
        return commentService.addComment(id, text);
    }
}
