package com.example.backend.service;

import com.example.backend.domain.Comment;

import java.util.List;

public interface CommentService {
    /**
     * Получает список комментариев для практики с указанным идентификатором,
     * сортированный по дате добавления в порядке убывания.
     *
     * @param practiceId идентификатор практики
     * @return список комментариев
     */
    List<Comment> getCommentsForPractice(Long practiceId);

    /**
     * Добавляет комментарий к практике с указанным идентификатором.
     * Автор комментария должен быть текущим пользователем, получаемым
     * через сервис Authorisation.
     *
     * @param practiceId идентификатор практики
     * @param text текст комментария
     * @return добавленный комментарий
     */
    Comment addCommentToPractice(Long practiceId, String text);
}
