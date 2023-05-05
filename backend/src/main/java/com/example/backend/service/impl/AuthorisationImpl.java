package com.example.backend.service.impl;

import com.example.backend.domain.Author;
import com.example.backend.repository.AuthorRepository;
import com.example.backend.service.Authorisation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthorisationImpl implements Authorisation {
    private final AuthorRepository authorRepository;

    public String getUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @Override
    public Author getCurrentUser() {
        // получаем имя текущего аутентифицированного пользователя
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        // ищем автора с соответствующим именем в БД
        Optional<Author> optionalAuthor = authorRepository.findByName(currentUsername);

        // если автор не найден, выбрасываем исключение
        if (optionalAuthor.isEmpty()) {
            throw new RuntimeException(currentUsername);
        }

        // возвращаем найденного автора
        return optionalAuthor.get();
    }
}
