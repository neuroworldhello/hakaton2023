package com.example.backend.service;

import com.example.backend.domain.Author;

public interface Authorisation {
    String getUserName();

    Author getCurrentUser();
}
