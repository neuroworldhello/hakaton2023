package com.example.backend.converter;

import com.example.backend.domain.Comment;
import com.example.backend.dto.CommentDto;

import java.util.List;

public interface CommentConverterService {
    CommentDto convertToDto(Comment comment);
    List<CommentDto> convertToDtoList(List<Comment> comments);
}
