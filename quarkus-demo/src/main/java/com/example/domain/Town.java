package com.example.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Town {
    @Id @GeneratedValue
    public Long id;
    @NotBlank
    public String name;
    @Size(min = 2, max = 3)
    public String countryCode;
}
