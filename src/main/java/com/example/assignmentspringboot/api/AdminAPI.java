package com.example.assignmentspringboot.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminAPI {
    @RequestMapping
    public String getAdmin() {
        return "Admin";
    }
}
