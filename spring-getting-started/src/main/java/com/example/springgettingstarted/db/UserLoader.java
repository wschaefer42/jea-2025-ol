package com.example.springgettingstarted.db;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
public class UserLoader {
    @Value("${default.language}")
    private String language;

    @Bean
    @Profile("testdata")
    public ApplicationRunner loadUsers(UserRepository userRepository) {
        return args -> {
            userRepository.saveAll(List.of(
                    User.of("Peter", "peter@example.com", 10, language),
                    User.of("Manuela", "manuela@example.com", 17, language)
            ));
        };
    }
}
