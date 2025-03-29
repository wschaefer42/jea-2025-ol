package org.example.jakartademo.university.persistence.entities;

import jakarta.persistence.Embeddable;

@Embeddable
public class Phone {
    private String areaCode;
    private String phoneNo;
}
