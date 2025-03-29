package org.example.jakartademo.university.persistence.entities;

import jakarta.persistence.Entity;

@Entity
public class Student extends People {
    private String grade;
}
