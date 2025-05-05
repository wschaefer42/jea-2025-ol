package com.example.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
public class Person extends PanacheEntity {
    @NotBlank
    public String firstName;
    @NotBlank
    public String lastName;
    @Column(unique = true)
    public String email;
    @ManyToOne
    public Town town;

    public static Person of(String firstName, String lastName, String email, Town town) {
        if (email == null) {
            email = firstName.toLowerCase() + "@example.com";
        }
        Person person = new Person();
        person.firstName = firstName;
        person.lastName = lastName;
        person.email = email;
        person.town = town;
        return person;
    }

    public static Person findByEmail(String email) {
        return find("email", email).firstResult();
    }

    public static List<Person> listByNames(String firstName, String lastName) {
        return list("firstName = ?1 and lastName = ?2", firstName, lastName);
    }

    @Nullable
    public static Person findByNameAndTown(String firstName, String lastName, String town) {
        return find("""
                from Person p
                join p.town t
                where p.firstName = ?1 and p.lastName = ?2 and t.name = ?3
                """.strip(), firstName, lastName, town).firstResult();
    }
}
