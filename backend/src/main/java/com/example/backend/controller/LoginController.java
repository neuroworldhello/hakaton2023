package com.example.backend.controller;

import com.example.backend.domain.Author;
import com.example.backend.repository.AuthorRepository;
import com.example.backend.service.Authorisation;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@RestController
public class LoginController {

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    Authorisation authorisation;

    @Autowired
    AuthorRepository authorRepository;

    @GetMapping("/login")
    public void login(HttpServletRequest req, HttpServletResponse resp, String username) throws IOException {
        UsernamePasswordAuthenticationToken authReq
                = new UsernamePasswordAuthenticationToken(username, null);
        Authentication auth = authManager.authenticate(authReq);

        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);
        HttpSession session = req.getSession(true);
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);

        Author author = authorRepository.findByName(authorisation.getUserName()).orElse(null);
        if (author == null){
            authorRepository.save(Author.builder()
                    .name(authorisation.getUserName())
                    .build());
        }

        resp.sendRedirect("/");
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.addCookie(new Cookie("JSESSIONID",""));
        resp.sendRedirect("/login.html");
    }

    @GetMapping("/username")
    public String username() throws IOException {
        return authorisation.getUserName();
    }
}
