package com.example.ebankbackend.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/public/test")
    public String publicEndpoint() {
        return "Public endpoint";
    }

    @GetMapping("/private/user")
    @PreAuthorize("hasRole('USER')")
    public String userEndpoint() {
        return "User endpoint";
    }

    @GetMapping("/private/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminEndpoint() {
        return "Admin endpoint";
    }
} 