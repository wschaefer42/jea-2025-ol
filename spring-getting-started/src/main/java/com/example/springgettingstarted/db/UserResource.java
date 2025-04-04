package com.example.springgettingstarted.db;

import org.apache.logging.log4j.util.Strings;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/users")
public class UserResource {
    private final Logger log;
    private final UserRepository userRepository;

    public UserResource(UserRepository userRepository, Logger log) {
        this.userRepository = userRepository;
        this.log = log;
    }

    @GetMapping
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @GetMapping("/query")
    public Iterable<User> findAllByName(@RequestParam @DefaultValue("") String name) {
        if (Strings.isNotBlank(name)) {
            return userRepository.findByNameContainsIgnoreCase(name);
        } else {
            return userRepository.findAll();
        }
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @GetMapping("/search")
    public User findByName(@RequestParam String name) {
        return userRepository.findByNameOrEmail(name);
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        var savedUser = userRepository.save(user);
        log.info(savedUser.toString());
        return ResponseEntity.ok(savedUser);
    }
}
