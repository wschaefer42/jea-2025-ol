package com.example.springgettingstarted.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@ToString
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String name;
    private String email;
    private Integer age;
    private String language = null;

    public static User of(String name, String email, Integer age, String language) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setAge(age);
        user.setLanguage(language);
        return user;
    }
}
