package com.example.backend.configuration;

import com.example.backend.service.Authorisation;
import com.example.backend.service.impl.AuthorisationImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public Authorisation authorisation(){
        return new AuthorisationImpl();
    }
}
