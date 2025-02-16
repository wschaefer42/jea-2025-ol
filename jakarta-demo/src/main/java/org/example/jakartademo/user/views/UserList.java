package org.example.jakartademo.user.views;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import org.example.jakartademo.user.persistence.Country;
import org.example.jakartademo.user.persistence.User;
import org.example.jakartademo.user.persistence.UserRepository;

import java.time.LocalDate;
import java.util.List;

@Named
@RequestScoped
@Getter
public class UserList {
    private List<User> users;

    @Inject
    private UserRepository userRepository;

    public LocalDate getLastModified() {
        return userRepository.getLastModified();
    }

    public void loadUsers() {
        users = userRepository.findAll();
    }

    public Country getCountry(String countryCode) {
        return Country.getCountryByCode(countryCode);
    }
}
