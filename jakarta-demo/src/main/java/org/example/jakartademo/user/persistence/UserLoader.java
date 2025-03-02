package org.example.jakartademo.user.persistence;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;
import java.util.List;

@Startup
@Singleton
public class UserLoader {
    @Inject
    private UserRepository userRepository;

    @PostConstruct
    public void init() {
        for (var name : List.of("Alice", "Bob", "Werner")) {
            if (userRepository.findByName(name) == null) {
                userRepository.saveUser(new User(name));
            }
        }
    }
}
