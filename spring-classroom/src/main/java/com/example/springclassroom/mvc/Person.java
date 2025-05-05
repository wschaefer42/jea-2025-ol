package com.example.springclassroom.mvc;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Person {
    @Id @GeneratedValue
    private Long id;
    @NotBlank
    @Column(unique = true)
    private String name;
    private String city;

    public static Person of(String name, String city) {
        Person person = new Person();
        person.setName(name);
        person.setCity(city);
        return person;
    }
}
