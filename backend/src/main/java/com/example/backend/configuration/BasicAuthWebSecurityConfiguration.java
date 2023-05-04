package com.example.backend.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class BasicAuthWebSecurityConfiguration
{

    @Autowired
    private CustomAuthenticationProvider authProvider;

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authProvider);
        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and().csrf()
                .disable()
       .authorizeHttpRequests((requests) -> requests
               .requestMatchers("/login.html").permitAll()
               .requestMatchers("/login").permitAll()
               .requestMatchers("/logout").permitAll()
               .requestMatchers("/style.css").permitAll()
               .requestMatchers("/script.js").permitAll()
                .anyRequest().authenticated()
        )
        .formLogin((form) -> form
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/", true)
                .permitAll()
        )
        .exceptionHandling().accessDeniedPage("/access-denied");
        return http.build();
    }


}
