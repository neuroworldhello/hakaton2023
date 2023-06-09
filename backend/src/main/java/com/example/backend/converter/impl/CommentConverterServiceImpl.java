package com.example.backend.converter.impl;

import com.example.backend.converter.CommentConverterService;
import com.example.backend.domain.Comment;
import com.example.backend.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentConverterServiceImpl implements CommentConverterService {
    private final ModelMapper modelMapper;

    @Override
    public CommentDto convertToDto(Comment comment) {
        CommentDto commentDto = modelMapper.map(comment, CommentDto.class);
        commentDto.setAuthor(comment.getAuthor().getName());
        commentDto.setCreatedAt(comment.getCreatedAt().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")));
        return commentDto;
    }

    @Override
    public List<CommentDto> convertToDtoList(List<Comment> comments) {
        return comments.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
