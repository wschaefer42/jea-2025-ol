package org.example.jakartademo.tests;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter @Setter @NoArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(length = 100, unique = true, nullable = false)
    String username;
    String email;

    public User(String name) {
        this.username = name;
        this.email = name + "@example.com";
    }
}
