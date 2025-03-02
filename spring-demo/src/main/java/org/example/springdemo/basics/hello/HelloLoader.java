package org.example.springdemo.basics.hello;

import lombok.extern.java.Log;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import java.util.Map;

@Log
@Configuration
@Profile("testdata")
public class HelloLoader {
    @Bean
    public ApplicationRunner loadHellos(HelloRepository repository) {
        return _ -> {
            int count = 0;
            for (var entity : Map.of("World", "Hello", "Peter", "Good Morning").entrySet()) {
                if (repository.findByAny(entity.getKey(), entity.getValue()) == null) {
                    repository.save(new Hello(entity.getKey(), entity.getValue()));
                    count++;
                }
            }
            log.info(String.format("Loaded %d Hello entries", count));
        };
    }
}
