package org.example.jakartademo.university.persistence.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Badge {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
