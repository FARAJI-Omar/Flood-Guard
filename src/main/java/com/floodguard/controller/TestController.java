package com.floodguard.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/public")
    public String publicEndpoint() {
        return "This is accessible to authenticated users";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminEndpoint() {
        return "This is only accessible to ADMIN role";
    }

    @GetMapping("/engineer")
    @PreAuthorize("hasRole('GIS_ENGINEER')")
    public String engineerEndpoint() {
        return "This is only accessible to GIS_ENGINEER role";
    }

    @GetMapping("/student")
    @PreAuthorize("hasRole('STUDENT')")
    public String studentEndpoint() {
        return "This is only accessible to STUDENT role";
    }
}
