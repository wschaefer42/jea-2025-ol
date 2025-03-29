package org.example.jakartademo.university.persistence.entities;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
    private String street;
    private String city;
}
