package com.example.springclassroom.db;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id @GeneratedValue
    private Long id;
    @NotBlank
    @Column(nullable = false, unique = true)
    private String name;
    private String email;
    private LocalDate birthDate;
    @Transient
    @Setter(AccessLevel.PRIVATE)
    private int age;
    @PostConstruct
    public void init() {
        age = LocalDate.now().getYear() - this.birthDate.getYear() + 1;
    }
}
