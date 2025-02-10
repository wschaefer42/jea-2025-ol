package org.example.jakartademo.tests;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;

import java.util.List;

@Named
@RequestScoped
@Getter
public class UserBean {
    private final String hello = "Hello";

    @Inject
    private UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void submit() {

    }
}
