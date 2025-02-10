package org.example.jakartademo.tests;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

@Startup
@Singleton
public class UserLoader {
    @Inject
    private UserRepository userRepository;

    @PostConstruct
    public void init() {
        userRepository.createUser(new User("John"));
        userRepository.createUser(new User("Jane"));
    }
}
