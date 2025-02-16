package org.example.jakartademo.user.persistence;

import java.util.List;

public record Country(String code, String name) {
    public static List<Country> getCountries() {
        return List.of(
                new Country("CH", "Switzerland"),
                new Country("DE", "Germany"),
                new Country("FR", "France"),
                new Country("US", "United States"),
                new Country("GB", "United Kingdom"));
    }

    public static Country getDefault() {
        return new Country("CH", "Switzerland");
    }

    public static Country getCountryByCode(String code) {
        return getCountries().stream()
                .filter(c -> c.code().equals(code))
                .findFirst()
                .orElseThrow();
    }
}
