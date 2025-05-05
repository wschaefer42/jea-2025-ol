package com.example.springclassroom.mvc;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PersonLoader {
    @Bean
    public ApplicationRunner applicationRunner(PersonRepository personRepository) {
        return args -> {
            personRepository.saveAll(List.of(
                    Person.of("Alice", "Paris"),
                    Person.of("Bob", "London")
            ));
        };
    }
}
