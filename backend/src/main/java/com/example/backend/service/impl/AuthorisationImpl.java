package com.example.backend.service.impl;

import com.example.backend.service.Authorisation;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthorisationImpl implements Authorisation {

    public String getUserName(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
