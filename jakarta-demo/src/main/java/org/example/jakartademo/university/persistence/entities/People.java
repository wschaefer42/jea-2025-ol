package org.example.jakartademo.university.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class People {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(nullable = false, unique = true)
    private String name;
    @Embedded
    private Phone phone;
    @ManyToOne(fetch = FetchType.LAZY)
    private University university;
    @OneToOne(optional = true)
    private Badge badge;
}
