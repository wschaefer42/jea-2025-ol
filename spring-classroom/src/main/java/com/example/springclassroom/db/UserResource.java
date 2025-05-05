package com.example.springclassroom.db;

import jakarta.inject.Inject;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/users")
public class UserResource {
    private final UserService userService;

    @Inject
    public UserResource(UserService userService) {
        this.userService = userService;
    }
}
